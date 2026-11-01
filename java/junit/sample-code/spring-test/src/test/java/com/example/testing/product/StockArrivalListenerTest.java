package com.example.testing.product;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.postgresql.PostgreSQLContainer;

/**
 * 第3部：KafkaListener のテスト（@SpringBootTest + Testcontainers）
 * <p>
 * 本物の Kafka にメッセージを送り、リスナーが受け取って在庫数を増やすまでを確かめる。
 * リスナーは別のスレッドで動くので、リスナーの処理が終わった時点で CountDownLatch を
 * countDown し、テストのスレッドはそれを await で待ってから確かめる。
 * <p>
 * テストデータは @Sql で登録し、結果は JdbcClient でテーブルを読んで確かめる。
 * @SpringBootTest はロールバックしないので、テストごとに別の商品ID（201・202）を使う。
 */
@SpringBootTest
@Testcontainers
@DisplayName("StockArrivalListener：Kafka")
class StockArrivalListenerTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:17-alpine");

    @Container
    @ServiceConnection
    static KafkaContainer kafka = new KafkaContainer("apache/kafka-native:4.0.0");

    /** 既定のトピック（spring.kafka.template.default-topic）へ送る */
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /** 本物のリスナーを包むスパイ。処理が終わったことを知るために使う */
    @MockitoSpyBean
    private StockArrivalListener stockArrivalListener;

    /** 検証用：テーブルを直接読む */
    @Autowired
    private JdbcClient jdbcClient;

    @Test
    @Sql(statements = """
            insert into products (id, name, price, stock) values (201, 'ボールペン', 120, 10);
            """)
    @DisplayName("入荷メッセージを受け取ると、在庫数が増える")
    void stockArrival() throws InterruptedException {
        // Arrange：リスナーの本物の処理が終わったら countDown する
        CountDownLatch processed = new CountDownLatch(1);
        Mockito.doAnswer(invocation -> {
            invocation.callRealMethod();
            processed.countDown();
            return null;
        }).when(stockArrivalListener).onMessage(ArgumentMatchers.any());

        // Act：キー＝商品ID、値＝入荷数 のメッセージを送る
        kafkaTemplate.sendDefault("201", "5");

        // Assert：処理が終わるまで待ってから、テーブルを確かめる
        Assertions.assertThat(processed.await(10, TimeUnit.SECONDS)).isTrue();
        Integer stock = jdbcClient.sql("select stock from products where id = 201")
                .query(Integer.class).single();
        Assertions.assertThat(stock).isEqualTo(15);
    }

    @Test
    @Sql(statements = """
            insert into products (id, name, price, stock) values (202, 'ノート', 200, 0);
            """)
    @DisplayName("入荷メッセージが続けて届くと、その分だけ在庫数が増える")
    void multipleArrivals() throws InterruptedException {
        // 2通分の処理が終わるまで待つ
        CountDownLatch processed = new CountDownLatch(2);
        Mockito.doAnswer(invocation -> {
            invocation.callRealMethod();
            processed.countDown();
            return null;
        }).when(stockArrivalListener).onMessage(ArgumentMatchers.any());

        kafkaTemplate.sendDefault("202", "3");
        kafkaTemplate.sendDefault("202", "4");

        Assertions.assertThat(processed.await(10, TimeUnit.SECONDS)).isTrue();
        Integer stock = jdbcClient.sql("select stock from products where id = 202")
                .query(Integer.class).single();
        Assertions.assertThat(stock).isEqualTo(7);
    }
}

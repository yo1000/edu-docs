package com.example.testing.product;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jdbc.test.autoconfigure.DataJdbcTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

/**
 * 第3部：Spring Data JDBC のテスト（@DataJdbcTest + Testcontainers）
 * <p>
 * テストデータは @Sql で登録し、テスト対象のリポジトリ（save など）では登録しない。
 * save の結果は、リポジトリを通さずに JdbcClient でテーブルを読んで確かめる。
 */
@DataJdbcTest
@Testcontainers
@DisplayName("ProductRepository：Spring Data JDBC")
class ProductRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:17-alpine");

    @Autowired
    private ProductRepository productRepository;

    /** 検証用：リポジトリを通さずにテーブルを読む */
    @Autowired
    private JdbcClient jdbcClient;

    @Test
    @Sql(statements = """
            insert into products (id, name, price, stock) values (101, 'ボールペン', 120, 10);
            """)
    @DisplayName("IDで商品を取得できる")
    void findById() {
        Optional<Product> found = productRepository.findById(101L);

        Assertions.assertThat(found).hasValue(new Product(101L, "ボールペン", 120, 10));
    }

    @Test
    @DisplayName("存在しないIDなら空の Optional を返す")
    void findByIdNotFound() {
        Optional<Product> found = productRepository.findById(999L);

        Assertions.assertThat(found).isEmpty();
    }

    @Test
    @Sql(statements = """
            insert into products (id, name, price, stock) values (101, 'ノート', 200, 30);
            insert into products (id, name, price, stock) values (102, 'ボールペン', 120, 10);
            insert into products (id, name, price, stock) values (103, '万年筆', 5000, 2);
            """)
    @DisplayName("指定した単価以下の商品を、安い順に返す")
    void findByPriceLessThanEqual() {
        List<Product> products = productRepository.findByPriceLessThanEqualOrderByPriceAsc(200);

        Assertions.assertThat(products)
                .extracting(Product::name)
                .containsExactly("ボールペン", "ノート");
    }

    @Test
    @DisplayName("新しい商品を保存すると、IDが採番されてテーブルに登録される")
    void saveNew() {
        Product saved = productRepository.save(new Product(null, "ボールペン", 120, 10));

        Product row = jdbcClient.sql("select id, name, price, stock from products where id = :id")
                .param("id", saved.id())
                .query(Product.class)
                .single();
        Assertions.assertThat(row).isEqualTo(new Product(saved.id(), "ボールペン", 120, 10));
    }

    @Test
    @Sql(statements = """
            insert into products (id, name, price, stock) values (101, 'ボールペン', 120, 10);
            """)
    @DisplayName("既存の商品を保存すると、テーブルの値が更新される")
    void saveExisting() {
        productRepository.save(new Product(101L, "ボールペン", 120, 15));

        Integer stock = jdbcClient.sql("select stock from products where id = 101")
                .query(Integer.class)
                .single();
        Assertions.assertThat(stock).isEqualTo(15);
    }
}

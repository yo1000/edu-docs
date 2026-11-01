package com.example.testing.product;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 入荷の知らせを受け取り、在庫数を増やす。
 * <p>
 * トピック名は spring.kafka.template.default-topic の値を使う。
 * メッセージのキーは商品ID、値は入荷数（どちらも文字列）。
 */
@Component
public class StockArrivalListener {

    private final ProductService productService;

    public StockArrivalListener(ProductService productService) {
        this.productService = productService;
    }

    @KafkaListener(topics = "${spring.kafka.template.default-topic}")
    public void onMessage(ConsumerRecord<String, String> record) {
        long productId = Long.parseLong(record.key());
        int quantity = Integer.parseInt(record.value());
        productService.addStock(productId, quantity);
    }
}

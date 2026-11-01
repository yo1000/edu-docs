package com.example.testing.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * 商品（products テーブル）。Spring Data JDBC のエンティティ。
 *
 * @param id    商品ID（新しい商品は null。保存すると採番される）
 * @param name  商品名
 * @param price 単価
 * @param stock 在庫数
 */
@Table("products")
public record Product(@Id Long id, String name, int price, int stock) {

    /** 在庫数を quantity だけ増やした商品を返す */
    public Product addStock(int quantity) {
        return new Product(id, name, price, stock + quantity);
    }
}

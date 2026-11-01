package com.example.testing.basic;

/**
 * 注文。
 *
 * @param productCode 商品コード
 * @param quantity    数量
 * @param totalPrice  支払金額（税込）
 */
public record Order(String productCode, int quantity, int totalPrice) {
}

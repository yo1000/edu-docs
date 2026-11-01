package com.example.testing.product;

/**
 * 商品登録 API のリクエストボディ。
 */
public record ProductRegisterRequest(String name, int price, int stock) {
}

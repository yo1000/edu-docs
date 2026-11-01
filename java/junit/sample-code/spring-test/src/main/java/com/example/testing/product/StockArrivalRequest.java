package com.example.testing.product;

/**
 * 入荷 API（POST /products/{id}/arrivals）のリクエストボディ。
 */
public record StockArrivalRequest(int quantity) {
}

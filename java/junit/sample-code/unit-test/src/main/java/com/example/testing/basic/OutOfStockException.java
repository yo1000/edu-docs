package com.example.testing.basic;

/**
 * 在庫が足りないときの例外。
 */
public class OutOfStockException extends RuntimeException {

    public OutOfStockException(String productCode, int stock, int quantity) {
        super("在庫が足りません: productCode=%s, stock=%d, quantity=%d"
                .formatted(productCode, stock, quantity));
    }
}

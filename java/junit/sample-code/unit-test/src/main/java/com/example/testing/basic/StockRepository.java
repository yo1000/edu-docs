package com.example.testing.basic;

/**
 * 在庫を管理する（実装は持たず、テストではモックに置き換える）。
 */
public interface StockRepository {

    /** 商品コードに対応する在庫数を返す */
    int findStock(String productCode);

    /** 在庫数を減らす */
    void decrease(String productCode, int quantity);
}

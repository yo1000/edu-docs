package com.example.testing.product;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

/**
 * 商品のリポジトリ（Spring Data JDBC）。
 * <p>
 * 商品をコレクションのように出し入れする窓口。save・findById などは ListCrudRepository が用意する。
 * insert・update といった SQL の都合はメソッド名に出さない。
 */
public interface ProductRepository extends ListCrudRepository<Product, Long> {

    /** 単価が maxPrice 以下の商品を、単価の安い順に返す（メソッド名から SQL が作られる） */
    List<Product> findByPriceLessThanEqualOrderByPriceAsc(int maxPrice);

    /** 在庫数が threshold 未満の商品を、在庫の少ない順に返す（演習5のテスト対象） */
    List<Product> findByStockLessThanOrderByStockAsc(int threshold);
}

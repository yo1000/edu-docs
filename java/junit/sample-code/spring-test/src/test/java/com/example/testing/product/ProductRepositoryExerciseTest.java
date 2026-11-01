package com.example.testing.product;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jdbc.test.autoconfigure.DataJdbcTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

/**
 * 演習5：ProductRepository.findByStockLessThanOrderByStockAsc のテストを書く
 * <p>
 * 仕様：在庫数が threshold 未満の商品を、在庫の少ない順に返す
 * <p>
 * テストデータは @Sql で登録する。テスト対象のリポジトリ（save など）では登録しない。
 * コンテナとリポジトリの用意は、書いてあるものを使う。
 */
@DataJdbcTest
@Testcontainers
@DisplayName("演習5：ProductRepository の在庫検索")
class ProductRepositoryExerciseTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:17-alpine");

    @Autowired
    private ProductRepository productRepository;

    // TODO 演習5-1：在庫数が 0・9・10・20 の4つの商品を @Sql で登録し、
    //   findByStockLessThanOrderByStockAsc(10) が、在庫 0 と 9 の商品を、この順に返すことを確かめる

    // TODO 演習5-2：在庫数が 10・20 の2つの商品を @Sql で登録し、
    //   findByStockLessThanOrderByStockAsc(10) が、空のリストを返すことを確かめる
}

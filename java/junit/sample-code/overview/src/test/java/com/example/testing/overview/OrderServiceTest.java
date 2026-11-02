package com.example.testing.overview;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * 第1部：1つのテストの中で、3つの道具が役割を分けている
 * <ul>
 *   <li>テスティングフレームワーク（JUnit）：@Test</li>
 *   <li>アサーションライブラリ（AssertJ）：Assertions.assertThat(…).isEqualTo(…)</li>
 *   <li>モッキングライブラリ（Mockito）：Mockito.mock(…)・Mockito.when(…)・Mockito.verify(…)</li>
 * </ul>
 * 1つずつの詳しい使い方は、第2部（unit-test）で扱う。
 */
class OrderServiceTest {

    @Test // JUnit：テストメソッドの目印
    void placeOrder() {
        // Mockito：モックを作り、振る舞いを決める
        StockRepository stockRepository = Mockito.mock(StockRepository.class);
        Mockito.when(stockRepository.findStock("A001")).thenReturn(10);
        OrderService orderService =
                new OrderService(stockRepository, new PriceCalculator(), Mockito.mock(OrderNotifier.class));

        Order order = orderService.placeOrder("A001", 1000, 2, MemberRank.GOLD);

        Assertions.assertThat(order.totalPrice()).isEqualTo(1980); // AssertJ：判定する
        Mockito.verify(stockRepository).decrease("A001", 2); // Mockito：呼び出しを確かめる
    }
}

package com.example.testing.basic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

/**
 * 第2部：Mockito によるモック
 * <p>
 * 依存先のモックとテスト対象は、各テストメソッドの Arrange で組み立てる。
 */
@DisplayName("OrderService：注文の受け付け")
class OrderServiceTest {

    @Test
    @DisplayName("在庫が足りれば、在庫を減らして注文を通知する")
    void placeOrder() {
        // Arrange：依存先をモックにして、テスト対象を組み立てる
        StockRepository stockRepository = Mockito.mock(StockRepository.class);
        OrderNotifier orderNotifier = Mockito.mock(OrderNotifier.class);
        OrderService orderService =
                new OrderService(stockRepository, new PriceCalculator(), orderNotifier);
        Mockito.when(stockRepository.findStock("A001")).thenReturn(10);

        // Act
        Order order = orderService.placeOrder("A001", 1000, 2, MemberRank.GOLD);

        // Assert
        Assertions.assertThat(order).isEqualTo(new Order("A001", 2, 1980));
        Mockito.verify(stockRepository).decrease("A001", 2);
        Mockito.verify(orderNotifier).notify(order);
    }

    @Test
    @DisplayName("通知した注文の中身を取り出して確かめる（ArgumentCaptor）")
    void notifiedOrder() {
        StockRepository stockRepository = Mockito.mock(StockRepository.class);
        OrderNotifier orderNotifier = Mockito.mock(OrderNotifier.class);
        OrderService orderService =
                new OrderService(stockRepository, new PriceCalculator(), orderNotifier);
        Mockito.when(stockRepository.findStock("A001")).thenReturn(10);

        orderService.placeOrder("A001", 1000, 3, MemberRank.SILVER);

        ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);
        Mockito.verify(orderNotifier).notify(captor.capture());
        Order notified = captor.getValue();
        Assertions.assertThat(notified.quantity()).isEqualTo(3);
        Assertions.assertThat(notified.totalPrice()).isEqualTo(3135);
    }

    @Test
    @DisplayName("注文を受け付ける前に、在庫を確認する")
    void checksStock() {
        StockRepository stockRepository = Mockito.mock(StockRepository.class);
        OrderNotifier orderNotifier = Mockito.mock(OrderNotifier.class);
        OrderService orderService =
                new OrderService(stockRepository, new PriceCalculator(), orderNotifier);
        Mockito.when(stockRepository.findStock("A001")).thenReturn(10);

        orderService.placeOrder("A001", 1000, 2, MemberRank.GOLD);

        // when で決めた呼び出しが、実際に使われたことを確かめる
        Mockito.verify(stockRepository).findStock("A001");
    }

    @Test
    @DisplayName("在庫が足りなければ例外になり、在庫も減らさず通知もしない")
    void outOfStock() {
        StockRepository stockRepository = Mockito.mock(StockRepository.class);
        OrderNotifier orderNotifier = Mockito.mock(OrderNotifier.class);
        OrderService orderService =
                new OrderService(stockRepository, new PriceCalculator(), orderNotifier);
        Mockito.when(stockRepository.findStock("A001")).thenReturn(1);

        Assertions.assertThatThrownBy(() -> orderService.placeOrder("A001", 1000, 2, MemberRank.REGULAR))
                .isInstanceOf(OutOfStockException.class);

        Mockito.verify(stockRepository, Mockito.never()).decrease(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt());
        Mockito.verify(orderNotifier, Mockito.never()).notify(ArgumentMatchers.any());
    }
}

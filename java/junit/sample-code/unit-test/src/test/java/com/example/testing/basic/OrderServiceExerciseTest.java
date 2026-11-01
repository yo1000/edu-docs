package com.example.testing.basic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * 演習4 の解答例
 */
@DisplayName("OrderService：演習")
class OrderServiceExerciseTest {

    @Test
    @DisplayName("在庫数と注文数が同じなら注文でき、在庫を注文数だけ減らす")
    void stockEqualsQuantity() {
        StockRepository stockRepository = Mockito.mock(StockRepository.class);
        OrderNotifier orderNotifier = Mockito.mock(OrderNotifier.class);
        OrderService orderService =
                new OrderService(stockRepository, new PriceCalculator(), orderNotifier);
        Mockito.when(stockRepository.findStock("B002")).thenReturn(5);

        Order order = orderService.placeOrder("B002", 200, 5, MemberRank.REGULAR);

        Assertions.assertThat(order).isEqualTo(new Order("B002", 5, 1100));
        Mockito.verify(stockRepository).decrease("B002", 5);
        Mockito.verify(orderNotifier).notify(order);
    }
}

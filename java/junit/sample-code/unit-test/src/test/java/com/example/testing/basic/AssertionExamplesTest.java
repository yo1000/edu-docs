package com.example.testing.basic;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 第2部：AssertJ によるアサーション
 */
@DisplayName("AssertJ のアサーション例")
class AssertionExamplesTest {

    @Test
    @DisplayName("数値")
    void numbers() {
        int total = new PriceCalculator().calculate(1000, 2, MemberRank.GOLD);

        Assertions.assertThat(total)
                .isEqualTo(1980)
                .isPositive()
                .isBetween(1000, 2000);
    }

    @Test
    @DisplayName("文字列")
    void strings() {
        String message = new OutOfStockException("A001", 1, 3).getMessage();

        Assertions.assertThat(message)
                .startsWith("在庫が足りません")
                .contains("productCode=A001")
                .endsWith("quantity=3");
    }

    @Test
    @DisplayName("リスト")
    void lists() {
        List<MemberRank> ranks = List.of(MemberRank.values());

        Assertions.assertThat(ranks)
                .hasSize(3)
                .contains(MemberRank.GOLD)
                .containsExactly(MemberRank.REGULAR, MemberRank.SILVER, MemberRank.GOLD)
                .doesNotContainNull();
    }

    @Test
    @DisplayName("リストの要素から値を取り出して比べる（extracting）")
    void extracting() {
        List<Order> orders = List.of(
                new Order("A001", 2, 1980),
                new Order("B002", 1, 1100));

        Assertions.assertThat(orders)
                .hasSize(2)
                .extracting(Order::productCode)
                .containsExactly("A001", "B002");

        Assertions.assertThat(orders)
                .extracting(Order::productCode, Order::totalPrice)
                .containsExactly(
                        Assertions.tuple("A001", 1980),
                        Assertions.tuple("B002", 1100));
    }

    @Test
    @DisplayName("Optional")
    void optional() {
        Assertions.assertThat(Optional.of("GOLD")).hasValue("GOLD");
        Assertions.assertThat(Optional.empty()).isEmpty();

        Optional<Order> found = Optional.of(new Order("A001", 2, 1980));
        Assertions.assertThat(found)
                .hasValueSatisfying(o -> Assertions.assertThat(o.totalPrice()).isEqualTo(1980));
    }

    @Test
    @DisplayName("例外")
    void exception() {
        PriceCalculator calculator = new PriceCalculator();

        Assertions.assertThatThrownBy(() -> calculator.calculate(1000, 0, MemberRank.REGULAR))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("数量は1以上にしてください: 0");
    }

    @Test
    @DisplayName("ソフトアサーション：失敗をまとめて報告する")
    void softAssertions() {
        Order order = new Order("A001", 2, 1980);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(order.productCode()).isEqualTo("A001");
            softly.assertThat(order.quantity()).isEqualTo(2);
            softly.assertThat(order.totalPrice()).isEqualTo(1980);
        });
    }
}

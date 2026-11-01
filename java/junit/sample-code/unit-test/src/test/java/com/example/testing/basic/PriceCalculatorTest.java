package com.example.testing.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * 第2部：JUnit の基本（JUnit 標準のアサーションだけを使う）
 * <p>
 * テスト対象は、各テストメソッドの Arrange で組み立てる。
 */
@DisplayName("PriceCalculator：支払金額の計算")
class PriceCalculatorTest {

    @Test
    @DisplayName("一般会員は割引なしで、消費税10%が加わる")
    void regularMember() {
        // Arrange（準備）
        PriceCalculator calculator = new PriceCalculator();

        // Act（実行）
        int actual = calculator.calculate(1000, 1, MemberRank.REGULAR);

        // Assert（検証）：期待値が先、検査される値が後
        Assertions.assertEquals(1100, actual);
    }

    @Test
    @DisplayName("ゴールド会員は10%引きのあとに消費税が加わる")
    void goldMember() {
        PriceCalculator calculator = new PriceCalculator();

        int actual = calculator.calculate(1000, 2, MemberRank.GOLD);

        Assertions.assertEquals(1980, actual);
    }

    @Test
    @DisplayName("1円未満の端数は切り捨てる")
    void roundDown() {
        PriceCalculator calculator = new PriceCalculator();

        // 999 × 95% = 949.05 → 949、949 × 110% = 1043.9 → 1043
        int actual = calculator.calculate(999, 1, MemberRank.SILVER);

        Assertions.assertEquals(1043, actual);
    }

    @Nested
    @DisplayName("入力値が不正なとき")
    class InvalidInput {

        @Test
        @DisplayName("数量が0なら IllegalArgumentException")
        void quantityZero() {
            PriceCalculator calculator = new PriceCalculator();

            IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class,
                    () -> calculator.calculate(1000, 0, MemberRank.REGULAR));

            Assertions.assertEquals("数量は1以上にしてください: 0", e.getMessage());
        }

        @Test
        @DisplayName("単価がマイナスなら IllegalArgumentException")
        void negativeUnitPrice() {
            PriceCalculator calculator = new PriceCalculator();

            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> calculator.calculate(-1, 1, MemberRank.REGULAR));
        }
    }
}

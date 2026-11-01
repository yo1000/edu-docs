package com.example.testing.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 第2部：パラメタライズドテストを使う前（値だけが違う同じ形のテストが並ぶ）
 * <p>
 * 同じテストを @ParameterizedTest で1つにまとめたものが PriceCalculatorParameterizedTest。
 */
class PriceCalculatorRepeatedTest {

    @Test
    void regular() {
        PriceCalculator calculator = new PriceCalculator();

        int actual = calculator.calculate(1000, 1, MemberRank.REGULAR);

        Assertions.assertEquals(1100, actual);
    }

    // silver()・gold() … と、値だけが違う同じ形のテストが続く

    @Test
    void silver() {
        PriceCalculator calculator = new PriceCalculator();

        int actual = calculator.calculate(1000, 1, MemberRank.SILVER);

        Assertions.assertEquals(1045, actual);
    }

    @Test
    void gold() {
        PriceCalculator calculator = new PriceCalculator();

        int actual = calculator.calculate(1000, 1, MemberRank.GOLD);

        Assertions.assertEquals(990, actual);
    }
}

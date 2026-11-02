package com.example.testing.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * 第2部：パラメタライズドテスト
 * <p>
 * 値のソースは @CsvSource(delimiter = '|', textBlock = """ … """) を基本にする。
 * 「#」で始まる行はコメントとして無視される（閉じる """ と同じ字下げで書く）。
 * <p>
 * アサーションは、ここまでに扱った JUnit 標準の Assertions を使う（AssertJ はこのあとで扱う）。
 */
@DisplayName("PriceCalculator：パラメタライズドテスト")
class PriceCalculatorParameterizedTest {

    @ParameterizedTest(name = "単価{0}円 × {1}個、{2} → {3}円")
    @CsvSource(delimiter = '|', textBlock = """
            # unitPrice | quantity | rank    | expected
                   1000 |        1 | REGULAR |     1100
                   1000 |        3 | SILVER  |     3135
                   1000 |        2 | GOLD    |     1980
                    999 |        1 | SILVER  |     1043
                      0 |        5 | GOLD    |        0
            """)
    void calculate(int unitPrice, int quantity, MemberRank rank, int expected) {
        PriceCalculator calculator = new PriceCalculator();

        int actual = calculator.calculate(unitPrice, quantity, rank);

        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "数量{0}個 → IllegalArgumentException")
    @CsvSource(delimiter = '|', textBlock = """
            # quantity
                     0
                    -1
                  -100
            """)
    @DisplayName("数量が1未満なら IllegalArgumentException")
    void invalidQuantity(int quantity) {
        PriceCalculator calculator = new PriceCalculator();

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate(1000, quantity, MemberRank.REGULAR));
    }
}

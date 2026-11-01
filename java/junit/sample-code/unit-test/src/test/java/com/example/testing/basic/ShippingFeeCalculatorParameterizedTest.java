package com.example.testing.basic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * 演習3：ShippingFeeCalculator のテストを、パラメタライズドテストで書き直す
 */
@DisplayName("ShippingFeeCalculator：パラメタライズドテスト")
class ShippingFeeCalculatorParameterizedTest {

    // TODO 演習3：@ParameterizedTest と @CsvSource(delimiter = '|', textBlock = """ … """) を使い、
    //   「購入金額 | 離島かどうか | 期待値」の組み合わせを1つのテストメソッドで確かめる
    @ParameterizedTest(name = "購入金額{0}円、離島={1} → 送料{2}円")
    @CsvSource(delimiter = '|', textBlock = """
            # totalPrice | remoteIsland | expected
                       0 | false        |      500
                    4999 | false        |      500
                    5000 | false        |        0
                    4999 | true         |     1500
                    5000 | true         |     1000
            """)
    void calculate(int totalPrice, boolean remoteIsland, int expected) {
        ShippingFeeCalculator calculator = new ShippingFeeCalculator();

        int actual = calculator.calculate(totalPrice, remoteIsland);

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}

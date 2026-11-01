package com.example.testing.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 演習：ShippingFeeCalculator のテストを書く
 * <p>
 * 仕様
 * <ul>
 *   <li>購入金額が 5,000 円以上なら送料は 0 円</li>
 *   <li>購入金額が 5,000 円未満なら送料は 500 円</li>
 *   <li>離島への配送は、上記に 1,000 円を加える</li>
 *   <li>購入金額が 0 円未満なら IllegalArgumentException</li>
 * </ul>
 * テスト対象は、各テストメソッドの Arrange で new して組み立てる。
 */
@DisplayName("ShippingFeeCalculator：送料の計算")
class ShippingFeeCalculatorTest {

    @Test
    @DisplayName("購入金額が5,000円未満なら送料は500円")
    void under5000() {
        ShippingFeeCalculator calculator = new ShippingFeeCalculator();

        int actual = calculator.calculate(4999, false);

        Assertions.assertEquals(500, actual);
    }

    // TODO 演習1：購入金額が 5,000 円ちょうどなら、送料が 0 円になることを確かめる

    // TODO 演習1：離島への配送で、購入金額が 4,999 円なら、送料が 1,500 円になることを確かめる

    // TODO 演習1：離島への配送で、購入金額が 5,000 円なら、送料が 1,000 円になることを確かめる

    // TODO 演習2：購入金額が -1 円なら IllegalArgumentException になり、
    //   メッセージが「購入金額は0以上にしてください: -1」であることを確かめる
}

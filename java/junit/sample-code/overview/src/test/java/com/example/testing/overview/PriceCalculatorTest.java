package com.example.testing.overview;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 第1部：テストコードで確かめる
 * <p>
 * ManualCheck（人が出力を目で見る）と同じことを、テストコードで確かめる。
 */
class PriceCalculatorTest {

    /** スライド「テストコード：コードの動きを確かめるコード」 */
    @Test
    void goldMember() {
        PriceCalculator calculator = new PriceCalculator();

        // 期待する値をコードに書いておく
        int total = calculator.calculate(1000, 2, MemberRank.GOLD);
        Assertions.assertEquals(1980, total);
    }

    /** スライド「テストの形：準備・実行・検証」 */
    @Test
    void regularMember() {
        // Arrange（準備）：テスト対象と入力値を用意する
        PriceCalculator calculator = new PriceCalculator();
        int unitPrice = 1000;
        int quantity = 1;

        // Act（実行）：テスト対象のメソッドを1回呼ぶ
        int actual = calculator.calculate(unitPrice, quantity, MemberRank.REGULAR);

        // Assert（検証）：結果が期待どおりかを確かめる
        Assertions.assertEquals(1100, actual);
    }

    /** 同じテストを Given-When-Then のコメントで書いたもの */
    @Test
    void regularMemberGivenWhenThen() {
        // given
        PriceCalculator calculator = new PriceCalculator();

        // when
        int actual = calculator.calculate(1000, 1, MemberRank.REGULAR);

        // then
        Assertions.assertEquals(1100, actual);
    }
}

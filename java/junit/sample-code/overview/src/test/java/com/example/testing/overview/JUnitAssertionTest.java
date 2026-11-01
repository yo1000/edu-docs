package com.example.testing.overview;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 第1部：アサーションライブラリ（同じ判定を、3つの書き方で）：JUnit 標準
 * <p>
 * JUnitAssertionTest・AssertJAssertionTest・HamcrestAssertionTest を並べて見比べる。
 */
class JUnitAssertionTest {

    @Test
    void goldMember() {
        int total = new PriceCalculator().calculate(1000, 2, MemberRank.GOLD);

        // 期待値が先、検査される値が後
        Assertions.assertEquals(1980, total);
    }
}

package com.example.testing.overview;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * 第1部：アサーションライブラリ（同じ判定を、3つの書き方で）：Hamcrest
 * <p>
 * JUnitAssertionTest・AssertJAssertionTest・HamcrestAssertionTest を並べて見比べる。
 */
class HamcrestAssertionTest {

    @Test
    void goldMember() {
        int total = new PriceCalculator().calculate(1000, 2, MemberRank.GOLD);

        // 判定をマッチャーの組み合わせで書く
        MatcherAssert.assertThat(total, Matchers.is(1980));
    }
}

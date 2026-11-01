package com.example.testing.overview;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 第1部：アサーションライブラリ（同じ判定を、3つの書き方で）：AssertJ
 * <p>
 * JUnitAssertionTest・AssertJAssertionTest・HamcrestAssertionTest を並べて見比べる。
 */
class AssertJAssertionTest {

    @Test
    void goldMember() {
        int total = new PriceCalculator().calculate(1000, 2, MemberRank.GOLD);

        // 検査される値から書き始め、「.」で判定をつなげる
        Assertions.assertThat(total).isEqualTo(1980);
    }
}

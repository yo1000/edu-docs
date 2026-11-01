package com.example.testing.basic;

/**
 * 会員ランク。ランクごとに割引率（%）を持つ。
 */
public enum MemberRank {
    REGULAR(0),
    SILVER(5),
    GOLD(10);

    private final int discountPercent;

    MemberRank(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int discountPercent() {
        return discountPercent;
    }
}

package com.example.testing.basic;

/**
 * 支払金額（税込）を計算する。
 * <p>
 * 小計（単価 × 数量）に会員ランクの割引を適用し、そこに消費税（10%）を加える。
 * 割引・税の計算で出た1円未満の端数は切り捨てる。
 */
public class PriceCalculator {

    private static final int TAX_RATE_PERCENT = 10;

    public int calculate(int unitPrice, int quantity, MemberRank rank) {
        if (unitPrice < 0) {
            throw new IllegalArgumentException("単価は0以上にしてください: " + unitPrice);
        }
        if (quantity < 1) {
            throw new IllegalArgumentException("数量は1以上にしてください: " + quantity);
        }
        int subtotal = unitPrice * quantity;
        int discounted = subtotal * (100 - rank.discountPercent()) / 100;
        return discounted * (100 + TAX_RATE_PERCENT) / 100;
    }
}

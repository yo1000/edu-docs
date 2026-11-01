package com.example.testing.basic;

/**
 * 送料を計算する（演習のテスト対象）。
 * <ul>
 *   <li>購入金額が 5,000 円以上なら送料は 0 円</li>
 *   <li>購入金額が 5,000 円未満なら送料は 500 円</li>
 *   <li>離島への配送は、上記に 1,000 円を加える</li>
 *   <li>購入金額が 0 円未満なら IllegalArgumentException</li>
 * </ul>
 */
public class ShippingFeeCalculator {

    static final int FREE_SHIPPING_THRESHOLD = 5_000;
    static final int BASE_FEE = 500;
    static final int REMOTE_ISLAND_FEE = 1_000;

    public int calculate(int totalPrice, boolean remoteIsland) {
        if (totalPrice < 0) {
            throw new IllegalArgumentException("購入金額は0以上にしてください: " + totalPrice);
        }
        int fee = totalPrice >= FREE_SHIPPING_THRESHOLD ? 0 : BASE_FEE;
        if (remoteIsland) {
            fee += REMOTE_ISLAND_FEE;
        }
        return fee;
    }
}

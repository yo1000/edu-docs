package com.example.testing.overview;

/**
 * 第1部：人が確かめる（main で呼び出して、出力を目で見る）
 * <p>
 * テストコードと比べるための例。実行すると 1980 が表示される。
 * 1980 が正しいかどうかは、毎回、人が判断しなければならない。
 * IDE から main メソッドを実行して、出力を確かめる。
 */
public class ManualCheck {

    public static void main(String[] args) {
        PriceCalculator calculator = new PriceCalculator();

        // main で呼び出して、出力を目で見る
        int total = calculator.calculate(1000, 2, MemberRank.GOLD);
        System.out.println(total);
    }
}

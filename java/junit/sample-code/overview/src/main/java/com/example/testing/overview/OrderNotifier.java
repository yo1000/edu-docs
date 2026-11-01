package com.example.testing.overview;

/**
 * 注文の確定を通知する（メール送信などを想定。テストではモックに置き換える）。
 */
public interface OrderNotifier {

    void notify(Order order);
}

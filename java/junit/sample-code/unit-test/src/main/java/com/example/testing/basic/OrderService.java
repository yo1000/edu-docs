package com.example.testing.basic;

/**
 * 注文を受け付ける。
 * <ol>
 *   <li>在庫を確認し、足りなければ OutOfStockException</li>
 *   <li>在庫を減らす</li>
 *   <li>支払金額を計算する</li>
 *   <li>注文の確定を通知する</li>
 * </ol>
 */
public class OrderService {

    // 在庫を調べる・減らす（データベース）、注文を通知する（メール）
    private final StockRepository stockRepository;
    private final PriceCalculator priceCalculator;
    private final OrderNotifier orderNotifier;

    public OrderService(StockRepository stockRepository,
                        PriceCalculator priceCalculator,
                        OrderNotifier orderNotifier) {
        this.stockRepository = stockRepository;
        this.priceCalculator = priceCalculator;
        this.orderNotifier = orderNotifier;
    }

    public Order placeOrder(String productCode, int unitPrice, int quantity, MemberRank rank) {
        int stock = stockRepository.findStock(productCode);
        if (stock < quantity) {
            throw new OutOfStockException(productCode, stock, quantity);
        }
        stockRepository.decrease(productCode, quantity);

        int totalPrice = priceCalculator.calculate(unitPrice, quantity, rank);
        Order order = new Order(productCode, quantity, totalPrice);
        orderNotifier.notify(order);
        return order;
    }
}

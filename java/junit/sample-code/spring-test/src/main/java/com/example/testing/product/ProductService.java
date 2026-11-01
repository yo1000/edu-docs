package com.example.testing.product;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /** 商品を照会する */
    public Optional<Product> lookup(long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Product register(String name, int price, int stock) {
        if (price < 0) {
            throw new IllegalArgumentException("単価は0以上にしてください: " + price);
        }
        if (stock < 0) {
            throw new IllegalArgumentException("在庫数は0以上にしてください: " + stock);
        }
        return productRepository.save(new Product(null, name, price, stock));
    }

    @Transactional
    public void addStock(long id, int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("入荷数は1以上にしてください: " + quantity);
        }
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.save(product.addStock(quantity));
    }
}

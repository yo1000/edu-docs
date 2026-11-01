package com.example.testing.product;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /** 商品を1件返す。見つからなければ 404 */
    @GetMapping("/{id}")
    public ResponseEntity<Product> lookup(@PathVariable("id") long id) {
        return productService.lookup(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /** 商品を登録し、201 と登録した商品の URL（Location ヘッダ）を返す */
    @PostMapping
    public ResponseEntity<Product> register(@RequestBody ProductRegisterRequest request,
                                            UriComponentsBuilder uriBuilder) {
        Product product = productService.register(request.name(), request.price(), request.stock());
        URI location = uriBuilder.path("/products/{id}")
                .buildAndExpand(product.id())
                .toUri();
        return ResponseEntity.created(location).body(product);
    }

    /** 入荷を受け付け、在庫を増やす。成功したら 204（演習7のテスト対象） */
    @PostMapping("/{id}/arrivals")
    public ResponseEntity<Void> arrive(@PathVariable("id") long id,
                                       @RequestBody StockArrivalRequest request) {
        productService.addStock(id, request.quantity());
        return ResponseEntity.noContent().build();
    }

    /** 商品が見つからなければ 404 */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Void> handleProductNotFound() {
        return ResponseEntity.notFound().build();
    }

    /** 入力値の誤りは 400 */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> handleIllegalArgument() {
        return ResponseEntity.badRequest().build();
    }
}

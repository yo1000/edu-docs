package com.example.testing.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;

/**
 * 演習7：入荷 API（POST /products/{id}/arrivals）のテストを書く
 * <p>
 * 仕様
 * <ul>
 *   <li>リクエスト本文は {"quantity": 入荷数}。ProductService.addStock(id, 入荷数) を呼ぶ</li>
 *   <li>成功したら 204（No Content）</li>
 *   <li>addStock が ProductNotFoundException を投げたら 404</li>
 * </ul>
 * ProductService はモック（@MockitoBean）にする。
 */
@WebMvcTest(ProductController.class)
@DisplayName("演習7：入荷 API")
class ProductControllerExerciseTest {

    @Autowired
    private MockMvcTester mvc;

    @MockitoBean
    private ProductService productService;

    // TODO 演習7-1：POST /products/1/arrivals に {"quantity": 5} を送ると、
    //   204 が返り、productService.addStock(1L, 5) が呼ばれたことを確かめる
    @Test
    @DisplayName("POST /products/{id}/arrivals：入荷できれば 204")
    void arrive() {
        MvcTestResult result = mvc.post().uri("/products/{id}/arrivals", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"quantity": 5}
                        """)
                .exchange();

        Assertions.assertThat(result).hasStatus(HttpStatus.NO_CONTENT);
        Mockito.verify(productService).addStock(1L, 5);
    }

    // TODO 演習7-2：productService.addStock(99L, 5) が ProductNotFoundException を投げるようにして、
    //   POST /products/99/arrivals に {"quantity": 5} を送ると、404 が返ることを確かめる
    //   ヒント：戻り値が void のメソッドは Mockito.doThrow(…).when(モック).メソッド(…) で振る舞いを決める
    @Test
    @DisplayName("POST /products/{id}/arrivals：商品が見つからなければ 404")
    void arriveNotFound() {
        Mockito.doThrow(new ProductNotFoundException(99L))
                .when(productService).addStock(99L, 5);

        MvcTestResult result = mvc.post().uri("/products/{id}/arrivals", 99)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"quantity": 5}
                        """)
                .exchange();

        Assertions.assertThat(result).hasStatus(HttpStatus.NOT_FOUND);
    }
}

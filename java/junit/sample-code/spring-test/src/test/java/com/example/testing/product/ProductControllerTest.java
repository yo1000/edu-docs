package com.example.testing.product;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;

/**
 * 第3部：RestController のテスト（@WebMvcTest + MockMvcTester）
 */
@WebMvcTest(ProductController.class)
@DisplayName("ProductController：REST API")
class ProductControllerTest {

    @Autowired
    private MockMvcTester mvc;

    @MockitoBean
    private ProductService productService;

    @Test
    @DisplayName("GET /products/{id}：見つかれば 200 と商品の JSON")
    void lookup() {
        // Arrange
        Mockito.when(productService.lookup(1L))
                .thenReturn(Optional.of(new Product(1L, "ボールペン", 120, 10)));

        // Act
        MvcTestResult result = mvc.get().uri("/products/{id}", 1).exchange();

        // Assert
        Assertions.assertThat(result)
                .hasStatusOk()
                .bodyJson()
                .isStrictlyEqualTo("""
                        {"id": 1, "name": "ボールペン", "price": 120, "stock": 10}
                        """);
    }

    @Test
    @DisplayName("GET /products/{id}：見つからなければ 404")
    void lookupNotFound() {
        Mockito.when(productService.lookup(99L)).thenReturn(Optional.empty());

        MvcTestResult result = mvc.get().uri("/products/{id}", 99).exchange();

        Assertions.assertThat(result).hasStatus(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("POST /products：登録できれば 201 と Location ヘッダ")
    void register() {
        Mockito.when(productService.register("ノート", 200, 30)).thenReturn(new Product(2L, "ノート", 200, 30));

        MvcTestResult result = mvc.post().uri("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"name": "ノート", "price": 200, "stock": 30}
                        """)
                .exchange();

        Assertions.assertThat(result)
                .hasStatus(HttpStatus.CREATED)
                .hasHeader("Location", "http://localhost/products/2");
        Mockito.verify(productService).register("ノート", 200, 30);
    }

    @Test
    @DisplayName("POST /products：入力値が不正なら 400")
    void registerBadRequest() {
        Mockito.when(productService.register(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
                .thenThrow(new IllegalArgumentException("単価は0以上にしてください: -1"));

        MvcTestResult result = mvc.post().uri("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"name": "ノート", "price": -1, "stock": 30}
                        """)
                .exchange();

        Assertions.assertThat(result).hasStatus(HttpStatus.BAD_REQUEST);
    }
}

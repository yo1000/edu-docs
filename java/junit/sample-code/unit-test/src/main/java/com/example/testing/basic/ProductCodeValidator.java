package com.example.testing.basic;

import java.util.regex.Pattern;

/**
 * 商品コードが正しい形式かを確かめる。
 * <p>
 * 商品コードは、英大文字1文字と数字3桁（例：A001）。
 * null・空文字・空白だけの文字列は、正しくない。
 */
public class ProductCodeValidator {

    private static final Pattern FORMAT = Pattern.compile("[A-Z][0-9]{3}");

    public boolean isValid(String code) {
        if (code == null || code.isBlank()) {
            return false;
        }
        return FORMAT.matcher(code).matches();
    }
}

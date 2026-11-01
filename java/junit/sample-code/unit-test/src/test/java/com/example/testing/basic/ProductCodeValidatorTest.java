package com.example.testing.basic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * 第2部：パラメタライズドテスト（文字列の引数に null・空文字・空白を渡す）
 * <p>
 * @CsvSource では、値の書き方で次のように渡される。
 * <ul>
 *   <li>何も書かない：null</li>
 *   <li>nullValues に指定した文字（ここでは null）：null</li>
 *   <li>''：空文字</li>
 *   <li>'   '：空白だけの文字列（クォートの中の空白は残る）</li>
 * </ul>
 */
@DisplayName("ProductCodeValidator：商品コードの形式")
class ProductCodeValidatorTest {

    @ParameterizedTest(name = "[{0}] → {1}")
    @CsvSource(delimiter = '|', nullValues = "null", textBlock = """
            # code  | expected
              A001  | true
              null  | false
                    | false
              ''    | false
              '   ' | false
              a001  | false
              A01   | false
            """)
    void isValid(String code, boolean expected) {
        ProductCodeValidator validator = new ProductCodeValidator();

        boolean actual = validator.isValid(code);

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}

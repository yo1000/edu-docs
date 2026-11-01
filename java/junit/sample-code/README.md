# edu-testing：テストコード研修 リポジトリ

Java 21 / JUnit 6 / AssertJ / Mockito / Spring Boot 4.1 / Testcontainers 2

講義のスライドと合わせて使います。スライドに出てくるコードは、すべてこのリポジトリにあります。
コードのあるファイルは、各スライドの下に表示しています。

## 構成

研修の3つの部を、それぞれ Maven のモジュールにしています。

| モジュール | 内容 | 演習 |
|---|---|---|
| `overview` | 第1部：テストコードの概要と構成要素（スライドのコード） | － |
| `unit-test` | 第2部：JUnit・パラメタライズドテスト・AssertJ・Mockito | 演習1〜4 |
| `spring-test` | 第3部：Spring Boot のテスト（JDBC・JPA・RestController・KafkaListener、Testcontainers） | 演習5〜7 |

- ルートの `pom.xml` は、3つのモジュールをまとめて実行するためだけのものです（`<modules>` だけを書いています）
- ルートの `mvnw`・`mvnw.cmd`・`.mvn/` は Maven Wrapper です
- 各モジュールは、ルートの `pom.xml` を `<parent>` にしていません。モジュールの pom だけでもビルドできます（例：`./mvnw test -f unit-test`）
  - `spring-test` は `spring-boot-starter-parent` を `<parent>` にしています
  - `overview`・`unit-test` は Spring を使いません。ライブラリのバージョンは Spring Boot 4.1.2 が管理しているものにそろえています
- テーブル定義：`spring-test/src/main/resources/schema.sql`
- テストデータ：各テストメソッドの中で用意します（Arrange で組み立てる・`@Sql`・`TestEntityManager`）。テスト対象のメソッド（`save` など）では登録しません

## 必要なもの

ターミナルで操作します。WSL・Linux・macOS などを想定しています。

- Java 21（JDK）
  - `java -version` で 21 と表示されることを確かめてください
  - Maven はインストールしなくてかまいません。リポジトリに入っている Maven Wrapper（`./mvnw`）が、初回の実行時に Maven をダウンロードして使います
- Docker（`spring-test` のテストで使います）
  - Testcontainers が、テストのたびに PostgreSQL と Kafka のコンテナを起動します
  - `docker info` が成功する状態にしておいてください

## テストの実行

リポジトリのフォルダ（この README があるフォルダ）で実行します。

すべてのモジュールのテストを実行する

```
./mvnw test
```

モジュールを指定して実行する（`-pl` にモジュールのフォルダ名）

```
./mvnw test -pl unit-test
```

テストクラスを指定して実行する（`-Dtest` にテストクラスの名前）

```
./mvnw test -pl unit-test -Dtest=PriceCalculatorTest
```

初回はライブラリと Docker イメージのダウンロードで数分かかります。
Windows のコマンドプロンプトでは `mvnw.cmd` を使います（研修ではターミナルの `./mvnw` を前提にしています）。

## 演習

演習のファイルに、テストメソッドを書き足して解きます。何をするかは、ファイルの `TODO` コメントとスライドに書いてあります。

| 演習 | 内容 | ファイル |
|---|---|---|
| 演習1・演習2 | JUnit の基本 | `unit-test/src/test/java/com/example/testing/basic/ShippingFeeCalculatorTest.java` |
| 演習3 | パラメタライズドテスト | `unit-test/src/test/java/com/example/testing/basic/ShippingFeeCalculatorParameterizedTest.java` |
| 演習4 | モック | `unit-test/src/test/java/com/example/testing/basic/OrderServiceExerciseTest.java`（新しく作る） |
| 演習5 | JDBC のテスト（`@DataJdbcTest`・`@Sql`） | `spring-test/src/test/java/com/example/testing/product/ProductRepositoryExerciseTest.java` |
| 演習6 | JPA のテスト（`@DataJpaTest`・`TestEntityManager`） | `spring-test/src/test/java/com/example/testing/member/MemberRepositoryExerciseTest.java` |
| 演習7 | RestController のテスト（`@WebMvcTest`・`MockMvcTester`） | `spring-test/src/test/java/com/example/testing/product/ProductControllerExerciseTest.java` |

演習のテストを実行する例

```
./mvnw test -pl spring-test -Dtest=ProductRepositoryExerciseTest
```

## ライセンス

講義スライド：© 2026 by yo1000 | Yoichi KIKUCHI is licensed under [CC BY-ND 4.0](https://creativecommons.org/licenses/by-nd/4.0/)

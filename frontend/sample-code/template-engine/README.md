# 第2部 テンプレートエンジン

Spring Boot＋Thymeleaf のプロジェクトです（Spring Boot 4.1、Java 21）。Maven Wrapper（`mvnw`）を同梱しています。

## コンテナで起動する

リポジトリのフォルダ（`frontend-training`）で実行します。

```bash
docker container run -d --name edu_java \
  -p 8080:8080 \
  -v "$PWD/template-engine/task-web:/work" -v ~/.m2:/root/.m2 -w /work \
  amazoncorretto:21 ./mvnw spring-boot:run

docker container logs -f edu_java    # 起動ログを見る（Ctrl+C でログの表示だけを終える）
```

`http://localhost:8080/tasks` を開きます。コードやテンプレートを変更したら、`docker container restart edu_java` で起動し直します。

## （補足）IDE で編集・確認する

IDE（IntelliJ IDEA など）で `task-web` を Maven プロジェクトとして開いて編集してもかまいません。PCに Java 21 があれば、`docker container stop edu_java` でコンテナを止めてから、IDE のターミナルで `./mvnw spring-boot:run` を実行して確認することもできます。

| ファイル | 役割 |
| --- | --- |
| `task-web/src/main/java/com/example/taskweb/Task.java` | タスクのデータ |
| `task-web/src/main/java/com/example/taskweb/TaskController.java` | 画面を返す処理と、JSONを返す処理（`/api/tasks`） |
| `task-web/src/main/resources/templates/tasks.html` | テンプレート |
| `task-web/src/main/resources/static/js/tasks.js` | 完了切替を `fetch` で行うJavaScript |
| `console/api-console.js` | 演習6で Console に貼り付けるコード |

演習5（通信の比較）と演習6（Console から API を呼ぶ）は、`http://localhost:8080/tasks` を開いて行います。

# 第3部 Node.js・TypeScript

`edu_frontend` の中で実行します。最初に依存ライブラリをインストールします。

```bash
docker container exec -it edu_frontend bash
cd node/task-api
npm install
```

| ファイル | スライド | 実行方法 |
| --- | --- | --- |
| `hello.js` | Node.jsの役割（演習7） | `node hello.js` |
| `taskUtil.js`・`hello-module.js` | npmとモジュール | `node hello-module.js` |
| `async1.js`〜`async3.js` | 非同期処理（演習8） | `node async1.js` など |
| `server.js` | ExpressでAPIを作る（演習9） | `npm start`（`node --watch server.js`） |
| `ts-samples/` | TypeScriptの基礎 | `npx tsc -p ts-samples`（型チェック）、`npx tsx ts-samples/erasure.ts`（実行） |
| `tsconfig.json`・`src/` | 演習10 | `npx tsc`（型チェック） |

演習10では、`server.js` を `src/index.ts` にコピーして TypeScript 化します。

第4部の画面は、この API（`localhost:3001`）を呼び出します。第4部の間も起動したままにします。

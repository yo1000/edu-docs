# 第1部 HTML/CSS・JavaScript

`html/` は `edu_html`（nginx）が配信します。エディタで保存して、ブラウザを再読み込み（F5）すると反映されます。

| ファイル | スライド | 開き方 |
| --- | --- | --- |
| `html/hello.html` | 確認環境を用意する | `http://localhost:8000/hello.html` |
| `html/tasks.html` | HTML・CSS、演習2 | `http://localhost:8000/tasks.html` |
| `html/selectors.html` | CSSセレクタ早見表 | `http://localhost:8000/selectors.html` |
| `html/specificity.html` | 組み合わせると詳細度が上がる | `http://localhost:8000/specificity.html` |
| `html/nth-child.html` | （補足）位置で選ぶ | `http://localhost:8000/nth-child.html` |
| `html/important.html` | （補足）!important | `http://localhost:8000/important.html` |
| `html/tasks-dom.html` | tasks.html にJavaScriptを足す | `http://localhost:8000/tasks-dom.html` |

`js/` のファイルは、DevTools の Console に貼り付けて実行します。どのページの Console でも動きます（`12-fetch.js` はインターネットに接続できる環境が必要です）。

| ファイル | スライド |
| --- | --- |
| `js/01-variables.js` | 変数は const と let |
| `js/02-object.js` | オブジェクトはクラスなしで作れる |
| `js/03-function.js` | 関数は「値」として扱える |
| `js/04-array.js` | 配列：ループよりメソッドを使う |
| `js/05-spread.js` | 分割代入とスプレッド構文 |
| `js/ex03-array.js` | 演習3 |
| `js/06-types.js` | 型は「値」に付いている |
| `js/07-falsy.js` | Falsy |
| `js/08-validate.js` | 入力チェックを1行で書く |
| `js/09-default.js` | デフォルト値を `\|\|` で与える |
| `js/10-nullish.js` | 0 や "" が正しい値のときは `??` |
| `js/11-equality.js` | 比較は常に `===` |
| `js/ex04-falsy.js` | 演習4 |
| `js/12-fetch.js` | fetch |

Console に貼り付けると警告が出て止められる場合は、表示の指示どおり `allow pasting` と入力して Enter を押し、貼り付け直します。別のファイルを試すときは、ページを再読み込み（F5）してから貼り付けます（同じ名前の `const` が重複するとエラーになるため）。

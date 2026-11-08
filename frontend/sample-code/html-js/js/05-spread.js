// 分割代入とスプレッド構文
// DevTools の Console に、1行ずつ（関数や複数行の文はまとめて）貼り付けて実行する。
// 別のファイルを試すときは、ページを再読み込み（F5）してから貼り付ける（同じ名前の const が重複するとエラーになるため）

const task = { id: 1, title: "設計書を読む", done: true };
const tasks = [task];

// 分割代入：プロパティを変数に取り出す
const { id, title } = task;

// オブジェクトのスプレッド：コピーして一部だけ変える（元の task は変更されない）
// 後ろに書いた同名のプロパティ（done）は、値の「置き換え」になる
const reopened = { ...task, done: false };
// → { id: 1, title: "設計書を読む", done: false }

// 配列のスプレッド：後ろに書いた要素は「追加」になる（置き換えではない）
const more = [...tasks, { id: 4, title: "テストする", done: false }];
// → 要素が2件の新しい配列。元の tasks は1件のまま

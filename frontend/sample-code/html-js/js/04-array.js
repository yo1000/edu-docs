// 配列
// DevTools の Console に、1行ずつ（関数や複数行の文はまとめて）貼り付けて実行する。
// 別のファイルを試すときは、ページを再読み込み（F5）してから貼り付ける（同じ名前の const が重複するとエラーになるため）

const tasks = [
  { id: 1, title: "設計書を読む", done: true },
  { id: 2, title: "APIを作る", done: false },
  { id: 3, title: "画面を作る", done: false },
];

tasks.filter((t) => !t.done); // 未完了の2件だけの配列
tasks.map((t) => t.title); // ["設計書を読む", "APIを作る", "画面を作る"]
tasks.find((t) => t.id === 2); // idが2のタスク（無ければ undefined）
tasks.some((t) => t.done); // true（1件でも完了があるか）

// つなげて書ける
tasks.filter((t) => !t.done).map((t) => t.title); // ["APIを作る", "画面を作る"]

// 順番に処理するだけなら for...of（Javaの拡張for文に相当）
for (const t of tasks) { console.log(t.title); }

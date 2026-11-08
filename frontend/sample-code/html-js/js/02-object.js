// オブジェクト
// DevTools の Console に、1行ずつ（関数や複数行の文はまとめて）貼り付けて実行する。
// 別のファイルを試すときは、ページを再読み込み（F5）してから貼り付ける（同じ名前の const が重複するとエラーになるため）

const task = { id: 1, title: "設計書を読む", done: false };

console.log(task.title); // 設計書を読む
task.done = true; // const でも中身は変更できる（Javaの final な参照と同じ）
task.memo = "急ぎ"; // 存在しないプロパティは追加される
console.log(task.owner); // undefined（エラーにはならない）

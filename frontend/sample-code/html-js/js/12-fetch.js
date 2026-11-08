// fetch：公開サンプルAPI（JSONPlaceholder）を呼ぶ
// DevTools の Console に、1行ずつ（関数や複数行の文はまとめて）貼り付けて実行する。
// 別のファイルを試すときは、ページを再読み込み（F5）してから貼り付ける（同じ名前の const が重複するとエラーになるため）

async function loadTodos() {
  const res = await fetch("https://jsonplaceholder.typicode.com/todos?_limit=3"); // 戻り値型は Promise<Response>
  if (!res.ok) throw new Error(`取得に失敗しました: ${res.status}`); // 200番台以外
  const todos = await res.json(); // JSONを変換。戻り値型は Promise<any>
  console.log(todos); // [{ userId: 1, id: 1, title: '…', completed: false }, …]
}
await loadTodos();
// 送信する場合（練習用のAPIなので、実際には保存されない）
const created = await fetch("https://jsonplaceholder.typicode.com/todos", {
  method: "POST",
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify({ title: "画面を作る", completed: false }),
});
console.log(created.status, await created.json()); // 201 と、送った内容に id が付いたもの

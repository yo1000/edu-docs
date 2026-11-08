// 演習6：Console から API を直接呼ぶ
// http://localhost:8080/tasks を開き、DevTools の Console に1つずつ貼り付けて実行する

// 1. 一覧を取得する
const res = await fetch("/api/tasks");
const tasks = await res.json();
console.log(tasks);

// 2. タスクを追加する（Network タブで通信も確かめる）
const created = await fetch("/api/tasks", {
  method: "POST",
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify({ title: "画面を作る" }),
});
console.log(created.status, await created.json());

// 3. await を付けないとどうなるか
const p = fetch("/api/tasks");
console.log(p); // Promise {<pending>}

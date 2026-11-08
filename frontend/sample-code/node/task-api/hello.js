const tasks = [
  { id: 1, title: "設計書を読む", done: true },
  { id: 2, title: "APIを作る", done: false },
];
const open = tasks.filter((t) => !t.done).map((t) => t.title);
console.log(`未完了: ${open.join(", ")}`);

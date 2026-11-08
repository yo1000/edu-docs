import express from "express";
import cors from "cors";

const app = express();
app.use(cors());            // 別オリジン（第4部の画面）からの呼び出しを許可する
app.use(express.json());    // リクエストボディのJSONを req.body に入れる

const tasks = [
  { id: 1, title: "設計書を読む", done: true },
  { id: 2, title: "APIを作る", done: false },
];
let nextId = 3;

// 一覧
app.get("/api/tasks", (req, res) => {
  res.json(tasks);
});

// 1件取得
app.get("/api/tasks/:id", (req, res) => {
  const task = tasks.find((t) => t.id === Number(req.params.id));
  if (!task) {
    return res.status(404).json({ message: "タスクが見つかりません" });
  }
  res.json(task);
});

// 追加
app.post("/api/tasks", (req, res) => {
  const title = req.body?.title?.trim();
  if (!title) {
    return res.status(400).json({ message: "title は必須です" });
  }
  const task = { id: nextId++, title, done: false };
  tasks.push(task);
  res.status(201).json(task);
});

// 完了状態の更新
app.patch("/api/tasks/:id", (req, res) => {
  const task = tasks.find((t) => t.id === Number(req.params.id));
  if (!task) {
    return res.status(404).json({ message: "タスクが見つかりません" });
  }
  task.done = req.body?.done ?? !task.done;   // 指定が無ければ反転する
  res.json(task);
});

app.listen(3001, () => {
  console.log("http://localhost:3001 で起動しました");
});

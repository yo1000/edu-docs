// 演習10の解答例
import express from "express";
import cors from "cors";

type Task = {
  id: number;
  title: string;
  done: boolean;
};

const app = express();
app.use(cors());
app.use(express.json());

const tasks: Task[] = [
  { id: 1, title: "設計書を読む", done: true },
  { id: 2, title: "APIを作る", done: false },
];
let nextId = 3;

// 同じ検索が3か所にあったので関数にまとめる
function findTask(id: string): Task | undefined {
  return tasks.find((t) => t.id === Number(id));
}

app.get("/api/tasks", (req, res) => {
  res.json(tasks);
});

app.get("/api/tasks/:id", (req, res) => {
  const task = findTask(req.params.id);
  if (!task) {
    return res.status(404).json({ message: "タスクが見つかりません" });
  }
  res.json(task);
});

app.post("/api/tasks", (req, res) => {
  // リクエストボディは外から来るデータ。中身は確かめるまで信用しない
  const body = req.body as { title?: unknown } | undefined;
  const title = typeof body?.title === "string" ? body.title.trim() : "";
  if (!title) {
    return res.status(400).json({ message: "title は必須です" });
  }
  const task: Task = { id: nextId++, title, done: false };
  tasks.push(task);
  res.status(201).json(task);
});

app.patch("/api/tasks/:id", (req, res) => {
  const task = findTask(req.params.id);
  if (!task) {
    return res.status(404).json({ message: "タスクが見つかりません" });
  }
  const body = req.body as { done?: unknown } | undefined;
  task.done = typeof body?.done === "boolean" ? body.done : !task.done;
  res.json(task);
});

app.delete("/api/tasks/:id", (req, res) => {
  const index = tasks.findIndex((t) => t.id === Number(req.params.id));
  if (index === -1) {
    return res.status(404).json({ message: "タスクが見つかりません" });
  }
  tasks.splice(index, 1);
  res.status(204).end();
});

app.listen(3001, () => {
  console.log("http://localhost:3001 で起動しました");
});

import { useEffect, useState } from "react";

type Task = {
  id: number;
  title: string;
  done: boolean;
};

type TaskItemProps = {
  task: Task;
  onToggle: (id: number) => void;     // 関数もpropsで渡せる
  onDelete: (id: number) => void;
};

function TaskItem({ task, onToggle, onDelete }: TaskItemProps) {
  return (
    <li className={task.done ? "done" : ""}>
      <span>{task.title}</span>
      <span>
        <button type="button" onClick={() => onToggle(task.id)}>完了切替</button>
        <button type="button" onClick={() => onDelete(task.id)}>削除</button>
      </span>
    </li>
  );
}

const API = "http://localhost:3001/api/tasks";

export default function App() {
  const [tasks, setTasks] = useState<Task[]>([]);     // 初期値を空にする
  const [title, setTitle] = useState("");
  const [message, setMessage] = useState("");

  // 最初に1回だけ、APIから一覧を取得する
  useEffect(() => {
    async function load() {
      const res = await fetch(API);
      setTasks(await res.json());
    }
    load();
  }, []);

  async function addTask() {
    if (!title.trim()) {
      setMessage("タイトルは必須です");
      return;
    }
    const res = await fetch(API, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ title }),
    });
    const created: Task = await res.json();
    setTasks([...tasks, created]);
    setTitle("");
    setMessage("");
  }

  async function toggleTask(id: number) {
    const res = await fetch(`${API}/${id}`, { method: "PATCH" });
    const updated: Task = await res.json();
    setTasks(tasks.map((t) => (t.id === id ? updated : t)));
  }

  // 演習12の解答例：DELETE /api/tasks/:id を呼んでから一覧から外す
  async function deleteTask(id: number) {
    const res = await fetch(`${API}/${id}`, { method: "DELETE" });
    if (!res.ok) return;
    setTasks(tasks.filter((t) => t.id !== id));
  }

  const openCount = tasks.filter((t) => !t.done).length;

  return (
    <main>
      <h1>タスク一覧</h1>
      {message && <p id="message">{message}</p>}
      {tasks.length === 0 && <p>タスクはありません</p>}

      <ul className="task-list">
        {tasks.map((task) => (
          <TaskItem key={task.id} task={task} onToggle={toggleTask} onDelete={deleteTask} />
        ))}
      </ul>
      <p>未完了：{openCount}件</p>

      <form
        onSubmit={(event) => {
          event.preventDefault();
          addTask();
        }}
      >
        <input
          type="text"
          value={title}
          onChange={(event) => setTitle(event.target.value)}
          placeholder="新しいタスク"
        />
        <button type="submit">追加</button>
      </form>
    </main>
  );
}

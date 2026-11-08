import { useState } from "react";

type Task = {
  id: number;
  title: string;
  done: boolean;
};

type TaskItemProps = {
  task: Task;
  onToggle: (id: number) => void;     // 関数もpropsで渡せる
};

function TaskItem({ task, onToggle }: TaskItemProps) {
  return (
    <li className={task.done ? "done" : ""}>
      <span>{task.title}</span>
      <button type="button" onClick={() => onToggle(task.id)}>完了切替</button>
    </li>
  );
}

export default function App() {
  const [tasks, setTasks] = useState<Task[]>([
    { id: 1, title: "設計書を読む", done: true },
    { id: 2, title: "APIを作る", done: false },
  ]);
  const [title, setTitle] = useState("");
  const [message, setMessage] = useState("");

  function addTask() {
    if (!title.trim()) {
      setMessage("タイトルは必須です");
      return;
    }
    const newTask: Task = { id: Date.now(), title: title.trim(), done: false };
    setTasks([...tasks, newTask]);
    setTitle("");
    setMessage("");
  }

  function toggleTask(id: number) {
    setTasks(tasks.map((t) => (t.id === id ? { ...t, done: !t.done } : t)));
  }

  const openCount = tasks.filter((t) => !t.done).length;

  return (
    <main>
      <h1>タスク一覧</h1>
      {message && <p id="message">{message}</p>}
      {tasks.length === 0 && <p>タスクはありません</p>}

      <ul className="task-list">
        {tasks.map((task) => (
          <TaskItem key={task.id} task={task} onToggle={toggleTask} />
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

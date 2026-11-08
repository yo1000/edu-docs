"use client";

import Link from "next/link";
import { useState } from "react";
import { API } from "./types";
import type { Task } from "./types";

export default function TaskList({ initialTasks }: { initialTasks: Task[] }) {
  const [tasks, setTasks] = useState(initialTasks);
  const [title, setTitle] = useState("");
  const [message, setMessage] = useState("");

  // 演習13 手順5の解答例：Vite版 App.tsx の addTask を移植
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

  return (
    <>
      {message && <p id="message">{message}</p>}
      <ul className="task-list">
        {tasks.map((task) => (
          <li key={task.id} className={task.done ? "done" : ""}>
            <span>
              <Link href={`/tasks/${task.id}`}>{task.title}</Link>
            </span>
            <button type="button" onClick={() => toggleTask(task.id)}>完了切替</button>
          </li>
        ))}
      </ul>
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
    </>
  );
}

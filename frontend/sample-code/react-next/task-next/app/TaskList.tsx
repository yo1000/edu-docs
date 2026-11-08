"use client";

import Link from "next/link";
import { useState } from "react";
import { API } from "./types";
import type { Task } from "./types";

export default function TaskList({ initialTasks }: { initialTasks: Task[] }) {
  const [tasks, setTasks] = useState(initialTasks);

  async function toggleTask(id: number) {
    const res = await fetch(`${API}/${id}`, { method: "PATCH" });
    const updated: Task = await res.json();
    setTasks(tasks.map((t) => (t.id === id ? updated : t)));
  }

  return (
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
  );
}

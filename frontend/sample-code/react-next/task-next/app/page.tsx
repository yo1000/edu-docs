import TaskList from "./TaskList";
import { API } from "./types";
import type { Task } from "./types";

// "use client" が無いコンポーネントは、サーバー側で実行される
export default async function Home() {
  const res = await fetch(API);
  const tasks: Task[] = await res.json();

  return (
    <main>
      <h1>タスク一覧</h1>
      <TaskList initialTasks={tasks} />
    </main>
  );
}

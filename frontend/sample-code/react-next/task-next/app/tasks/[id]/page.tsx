import Link from "next/link";
import { notFound } from "next/navigation";
import { API } from "../../types";
import type { Task } from "../../types";

type Props = {
  params: Promise<{ id: string }>;
};

export default async function TaskDetail({ params }: Props) {
  const { id } = await params;

  const res = await fetch(`${API}/${id}`);
  if (!res.ok) {
    notFound();   // 404ページを表示する
  }
  const task: Task = await res.json();

  return (
    <main>
      <h1>タスク #{task.id}</h1>
      <p>タイトル：{task.title}</p>
      <p>状態：{task.done ? "完了" : "未完了"}</p>
      <Link href="/">一覧へ戻る</Link>
    </main>
  );
}

type Task = {
  id: number;
  title: string;
  done: boolean;
};

const tasks: Task[] = [
  { id: 1, title: "設計書を読む", done: true },
  { id: 2, title: "APIを作る", done: false },
];

const found = tasks.find((t) => t.id === 2); // 型は Task | undefined

console.log(found.title); // エラー: 'found' is possibly 'undefined'.

if (!found) {
  console.log("見つかりません");
} else {
  console.log(found.title); // OK。このブロック内では Task 型に絞り込まれている
}

console.log(found?.title ?? "（なし）"); // OK。?. と ?? もそのまま使える

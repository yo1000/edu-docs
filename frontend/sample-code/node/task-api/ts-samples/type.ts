type Task = {
  id: number;
  title: string;
  done: boolean;
  memo?: string; // ? を付けると省略可能（型は string | undefined になる）
};

const task: Task = { id: 1, title: "設計書を読む", done: true };
function label(task: Task): string {
  return `#${task.id} ${task.title}`;
}

// Task と宣言していないが、必要なプロパティをすべて持っているのでOK
const row = { id: 9, title: "DBから取得した行", done: false, createdBy: "sato" };
label(row);

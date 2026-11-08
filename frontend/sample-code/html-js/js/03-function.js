// 関数
// DevTools の Console に、1行ずつ（関数や複数行の文はまとめて）貼り付けて実行する。
// 別のファイルを試すときは、ページを再読み込み（F5）してから貼り付ける（同じ名前の const が重複するとエラーになるため）

// 関数宣言：引数にも戻り値にも型を書かない
function label(task) {
  return `#${task.id} ${task.title}`;
}

// アロー関数：Javaのラムダ式に相当（矢印は -> ではなく =>）
const isOpen = (task) => !task.done;

const task = { id: 2, title: "APIを作る", done: false };
console.log(label(task)); // #2 APIを作る
console.log(isOpen(task)); // true

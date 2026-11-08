// 演習4：予想してから、Consoleで確かめる
// DevTools の Console に、1行ずつ（関数や複数行の文はまとめて）貼り付けて実行する。
// 別のファイルを試すときは、ページを再読み込み（F5）してから貼り付ける（同じ名前の const が重複するとエラーになるため）

// 問1　結果を予想して紙に書いてから実行する
Boolean(" "); // 【1】 空白1文字
Boolean("0"); // 【2】
Boolean(Number("0")); // 【3】
["a", "", undefined, "b"].filter(Boolean); // 【4】
undefined ?? "x"; // 【5】
"" ?? "x"; // 【6】
"" || "x"; // 【7】
// 解答：【1】 true  【2】 true  【3】 false  【4】 ["a", "b"]  【5】 "x"  【6】 ""  【7】 "x"

// 問2　関数の中身を1行で書く
// task.assignee は { name: "佐藤" } のことも、プロパティ自体が無い（undefined の）こともある
// 担当者名を返す。取れなければ "未割当" を返す
function assigneeName(task) {
  // 解答例：名前が空文字列の場合も「未割当」にしたいので、?? ではなく || を使う
  return task.assignee?.name || "未割当";
}

assigneeName({ assignee: { name: "佐藤" } });   // "佐藤" になればよい
assigneeName({ assignee: { name: "" } });       // "未割当" になればよい
assigneeName({});                               // "未割当" になればよい

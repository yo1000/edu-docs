// 活用【2】：デフォルト値
// DevTools の Console に、1行ずつ（関数や複数行の文はまとめて）貼り付けて実行する。
// 別のファイルを試すときは、ページを再読み込み（F5）してから貼り付ける（同じ名前の const が重複するとエラーになるため）

function greet(user) {
  const name = user?.profile?.nickname || user?.name || "ゲスト";
  return `こんにちは、${name}さん`;
}

greet(); // こんにちは、ゲストさん
greet({ name: "佐藤" }); // こんにちは、佐藤さん
greet({ name: "佐藤", profile: { nickname: "" } }); // こんにちは、佐藤さん（空文字は飛ばす）
greet({ name: "佐藤", profile: { nickname: "さとう" } }); // こんにちは、さとうさん

// 活用【1】：入力チェック
// DevTools の Console に、1行ずつ（関数や複数行の文はまとめて）貼り付けて実行する。
// 別のファイルを試すときは、ページを再読み込み（F5）してから貼り付ける（同じ名前の const が重複するとエラーになるため）

function validate(title) {
  if (!title?.trim()) {   // Javaなら title != null && !title.isBlank()
    return "タイトルは必須です";
  }
  return undefined;   // 問題なし
}

validate(undefined);    // "タイトルは必須です"
validate("");           // "タイトルは必須です"
validate("   ");        // "タイトルは必須です"
validate("APIを作る");  // undefined

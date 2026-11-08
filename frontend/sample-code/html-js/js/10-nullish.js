// 0 や "" が正しい値のときは ??
// DevTools の Console に、1行ずつ（関数や複数行の文はまとめて）貼り付けて実行する。
// 別のファイルを試すときは、ページを再読み込み（F5）してから貼り付ける（同じ名前の const が重複するとエラーになるため）

const query = { size: 0 };
query.size || 20;   // 20 ← 0を指定したのに
query.size ?? 20;   // 0

const settings = { debug: false };
settings.debug || true;   // true ← 誤り
settings.debug ?? true;   // false

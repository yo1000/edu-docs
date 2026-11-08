// 型
// DevTools の Console に、1行ずつ（関数や複数行の文はまとめて）貼り付けて実行する。
// 別のファイルを試すときは、ページを再読み込み（F5）してから貼り付ける（同じ名前の const が重複するとエラーになるため）

typeof 42;          // "number"
typeof "abc";       // "string"
typeof true;        // "boolean"
typeof undefined;   // "undefined"
typeof { id: 1 };   // "object"
typeof [1, 2];      // "object" ← 配列も object。配列かどうかは Array.isArray(x) で判定する
typeof (() => 1);   // "function"

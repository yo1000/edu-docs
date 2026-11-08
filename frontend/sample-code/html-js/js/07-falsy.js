// Falsy と Truthy
// DevTools の Console に、1行ずつ（関数や複数行の文はまとめて）貼り付けて実行する。
// 別のファイルを試すときは、ページを再読み込み（F5）してから貼り付ける（同じ名前の const が重複するとエラーになるため）

// Falsy：if で false 扱いになる6つの値
Boolean(false);       // false
Boolean(0);           // false
Boolean("");          // false
Boolean(null);        // false
Boolean(undefined);   // false
Boolean(NaN);         // false

// 直感に反して Truthy になる値
Boolean("0");       // true ← 空でない文字列はすべてTruthy
Boolean("false");   // true
Boolean([]);        // true ← 空の配列でもTruthy。空かどうかは tasks.length === 0 で判定する
Boolean({});        // true ← 空のオブジェクトでもTruthy

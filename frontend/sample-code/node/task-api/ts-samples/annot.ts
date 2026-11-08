const title: string = "設計書を読む";
const count: number = 3;
const done: boolean = false;
const titles: string[] = ["A", "B"];

// 初期値から型が分かる場合は省略できる（Javaの var と同じ）
const message = "こんにちは"; // string と推論される
// 関数は引数と戻り値に型を書く
function add(a: number, b: number): number {
  return a + b;
}

add(1, "2"); // エラー: Argument of type 'string' is not assignable to parameter of type 'number'.

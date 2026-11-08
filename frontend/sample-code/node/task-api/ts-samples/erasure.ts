type Task = {
  id: number;
  title: string;
  done: boolean;
};

// コンパイルエラーにも実行時エラーにもならない
const data = JSON.parse('{"id":"1","title":123}') as Task;

console.log(typeof data.id); // "string"（型の上では number のはず）

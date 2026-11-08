type Status = "todo" | "doing" | "done";

function statusLabel(status: Status): string {
  switch (status) {
    case "todo": return "未着手";
    case "doing": return "作業中";
    case "done": return "完了";
  }
}

statusLabel("doing"); // OK
statusLabel("finish"); // エラー: 定義に無い文字列は渡せない

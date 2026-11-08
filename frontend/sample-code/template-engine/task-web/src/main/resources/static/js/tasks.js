const list = document.querySelector(".task-list");
const message = document.querySelector("#message");

list.addEventListener("click", async (event) => {
  if (!event.target.matches("button")) return;

  const li = event.target.closest("li");
  const res = await fetch(`/api/tasks/${li.dataset.id}/toggle`, { method: "POST" });
  if (!res.ok) {
    message.textContent = "更新に失敗しました";
    return;
  }

  const task = await res.json();
  li.classList.toggle("done", task.done);   // サーバーの結果に合わせて付け外しする
});

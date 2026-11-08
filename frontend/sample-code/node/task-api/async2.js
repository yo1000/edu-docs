import { readFile } from "node:fs/promises";

const text = await readFile("package.json", "utf8");   // 戻り値型は Promise<string>
const pkg = JSON.parse(text);
console.log(pkg.name);                                 // task-api

// 失敗は try/catch で受ける（Javaと同じ形）
try {
  await readFile("存在しない.txt", "utf8");
} catch (e) {
  console.log(`読めませんでした: ${e.code}`);          // 読めませんでした: ENOENT
}

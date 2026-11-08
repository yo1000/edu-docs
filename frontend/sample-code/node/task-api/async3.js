const wait = (ms, value) => new Promise((resolve) => setTimeout(() => resolve(value), ms));

console.time("順番に");
const a = await wait(1000, "A");
const b = await wait(1000, "B");
console.timeEnd("順番に");            // 約2秒

console.time("同時に");
// Promise.all の戻り値型も Promise なので await する
const [c, d] = await Promise.all([wait(1000, "C"), wait(1000, "D")]);
console.timeEnd("同時に");            // 約1秒

export function openTitles(tasks) {
  return tasks.filter((t) => !t.done).map((t) => t.title);
}

export const MAX_TITLE_LENGTH = 50;

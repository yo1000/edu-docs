# 第4部 React・Next.js

`edu_frontend` の中で実行します。`node/task-api` の API（`npm start`）を、別のターミナルで起動しておきます。

## task-ui（Vite＋React）

```bash
docker container exec -it edu_frontend bash
cd react-next/task-ui
npm install
npm run dev -- --host        # http://localhost:5173（コンテナ内では --host が必要）
```

| ファイル | スライド |
| --- | --- |
| `src/App.tsx` | コンポーネント・props、stateとイベント（演習11）。演習12で、APIを呼ぶ形に書き換える |
| `src/index.css` | 第1部の tasks.html と同じCSS |

## task-next（Next.js）

```bash
cd react-next/task-next
npm install
npm run dev                  # http://localhost:3000
```

| ファイル | 役割 |
| --- | --- |
| `app/layout.tsx` | 全ページ共通の外枠 |
| `app/types.ts` | Task 型 |
| `app/page.tsx` | 一覧ページ（サーバーコンポーネント） |
| `app/loading.tsx` | 読み込み中の表示 |
| `app/TaskList.tsx` | 完了切替を行う部分（クライアントコンポーネント） |
| `app/tasks/[id]/page.tsx` | 詳細ページ（動的ルート） |

演習13は、このプロジェクトで行います。

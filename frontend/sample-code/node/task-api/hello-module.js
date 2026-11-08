// hello.js をモジュール版に書き換えた例
import { openTitles, MAX_TITLE_LENGTH } from "./taskUtil.js";   // 自分のファイルは ./ と拡張子を付ける
import express from "express";                                   // ライブラリは名前だけ

console.log(openTitles([{ id: 1, title: "APIを作る", done: false }]));

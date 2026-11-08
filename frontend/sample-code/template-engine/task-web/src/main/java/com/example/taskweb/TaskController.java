package com.example.taskweb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class TaskController {

    // 研修用にメモリ上で保持する（本来はデータベース）
    private final List<Task> tasks = new ArrayList<>(List.of(
            new Task(1, "設計書を読む", true),
            new Task(2, "APIを作る", false)));
    private int nextId = 3;

    // ---- 画面を返す処理 ----

    // 画面を返す：戻り値はテンプレート名
    @GetMapping("/tasks")
    public String list(Model model) {
        model.addAttribute("tasks", tasks);
        return "tasks";                       // templates/tasks.html が使われる
    }

    // フォーム送信を受け取る：処理後は一覧へリダイレクト
    @PostMapping("/tasks")
    public String add(@RequestParam("title") String title) {
        if (!title.isBlank()) {
            tasks.add(new Task(nextId++, title.trim(), false));
        }
        return "redirect:/tasks";
    }

    // ---- JSONを返す処理（JavaScriptから呼ばれる） ----

    // 一覧をJSONで返す
    @GetMapping("/api/tasks")
    @ResponseBody                             // 戻り値をテンプレート名ではなくJSONとして返す
    public List<Task> listApi() {
        return tasks;                         // [{"id":1,"title":"設計書を読む","done":true}, ...]
    }

    // ボディのJSON {"title":"..."} を受け取る型
    record NewTask(String title) {}

    // JSONを受け取って追加する
    @PostMapping("/api/tasks")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)       // 201 Created を返す
    public Task create(@RequestBody NewTask body) {   // ボディのJSONを NewTask に変換する
        if (body.title() == null || body.title().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Task task = new Task(nextId++, body.title().trim(), false);
        tasks.add(task);
        return task;                          // {"id":3,"title":"画面を作る","done":false}
    }

    // 完了状態を切り替える
    @PostMapping("/api/tasks/{id}/toggle")
    @ResponseBody
    public Task toggle(@PathVariable("id") int id) {
        Task task = tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        task.setDone(!task.isDone());
        return task;                          // {"id":2,"title":"APIを作る","done":true}
    }
}

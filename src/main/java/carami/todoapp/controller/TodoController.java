package carami.todoapp.controller;

import carami.todoapp.entity.Todo;
import carami.todoapp.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // 전체 ToDo 리스트 조회
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        List<Todo> todos = todoService.findAll();
        return ResponseEntity.ok(todos);
    }

    // 단일 ToDo 조회
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable("id") Long id) {
        return todoService.findById(id)
                .map(ResponseEntity::ok)        // 값이 있으면 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build()); // 없으면 404
    }

    // ToDo 생성
    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo saved = todoService.save(todo);
        return ResponseEntity.ok(saved);
    }

    // ToDo 수정 (전체 업데이트)
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable("id") Long id, @RequestBody Todo updatedTodo) {
        if (!todoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedTodo.setId(id); // 기존 id를 유지
        Todo saved = todoService.save(updatedTodo);
        return ResponseEntity.ok(saved);
    }

    // 일부 필드만 수정 (Patch)
    @PatchMapping("/{id}")
    public ResponseEntity<Todo> patchTodo(@PathVariable("id") Long id, @RequestBody Todo partialTodo) {
        return todoService.findById(id)
                .map(existing -> {
                    if (partialTodo.getTitle() != null) {
                        existing.setTitle(partialTodo.getTitle());
                    }
                    existing.setCompleted(partialTodo.isCompleted());
                    Todo saved = todoService.save(existing);
                    return ResponseEntity.ok(saved);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ToDo 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("id") Long id) {
        if (!todoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        todoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

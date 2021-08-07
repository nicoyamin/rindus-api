package com.example.rindus.controller;

import com.example.rindus.entity.Todo;
import com.example.rindus.model.TodoRequest;
import com.example.rindus.service.TodoService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class TodoController implements TodoApi{
    
    private TodoService todoService;


    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @SneakyThrows
    @Override
    public ResponseEntity<List<Todo>> getTodos(boolean extractJson, boolean extractXml) {
        List<Todo> todos = todoService.getTodos(extractJson, extractXml);
        return ResponseEntity.ok(todos);
    }

    @Override
    public ResponseEntity<Todo> postTodo(@Valid TodoRequest request) {
        Todo createdTodo = todoService.postTodo(request);
        return ResponseEntity.ok(createdTodo);
    }

    @Override
    public ResponseEntity<Todo> putTodo(@Valid TodoRequest request) {
        Todo updatedTodo = todoService.putTodo(request);
        return ResponseEntity.ok(updatedTodo);
    }

    @Override
    public ResponseEntity<Todo> patchTodo(@Valid TodoRequest request) {

        Todo updatedTodo = todoService.patchTodo(request);
        return ResponseEntity.ok(updatedTodo);
    }

    @Override
    public ResponseEntity deleteTodo(int todoId) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo with Id " + todoId + " deleted successfully");
    }
}

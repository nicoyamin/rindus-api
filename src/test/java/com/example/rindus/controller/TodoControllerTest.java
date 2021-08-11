package com.example.rindus.controller;

import com.example.rindus.entity.Todo;
import com.example.rindus.exception.ResourceFormatException;
import com.example.rindus.model.TodoRequest;
import com.example.rindus.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TodoControllerTest {

    @Mock
    TodoService service;

    @Mock
    TodoRequest request;

    @Mock
    Todo todo;

    TodoController controller;

    @Mock
    List<Todo> todos;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller= new TodoController(service);
    }

    @Test
    public void shouldGetTodosAndReturnTodoListAnd200Response() throws IOException {


        when(service.getTodos(any(Boolean.class), any(Boolean.class))).thenReturn(todos);

        ResponseEntity response = controller.getTodos(false, false);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), todos);
    }

    @Test
    public void shouldCreateTodoAndReturnCreatedTodoAnd200Response() throws IOException, ResourceFormatException {


        when(service.postTodo(request)).thenReturn(todo);

        ResponseEntity response = controller.postTodo(request);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), todo);
    }

    @Test
    public void shouldUpdateTodoAndReturnUpdatedTodoAnd200Response() throws IOException, ResourceFormatException {


        when(service.putTodo(request)).thenReturn(todo);

        ResponseEntity response = controller.putTodo(request);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), todo);
    }

    @Test
    public void shouldPatchTodoAndReturnPatchedTodoAnd200Response() throws IOException, ResourceFormatException {


        when(service.patchTodo(request)).thenReturn(todo);

        ResponseEntity response = controller.patchTodo(request);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), todo);
    }

    @Test
    public void shouldDeleteTodoAndReturnDeletedTodoIdAnd200Response() throws IOException, ResourceFormatException {


        when(service.deleteTodo(1)).thenReturn(1);

        ResponseEntity response = controller.deleteTodo(1);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), 1);
    }
}

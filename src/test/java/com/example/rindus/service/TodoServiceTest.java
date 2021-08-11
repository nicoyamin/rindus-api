package com.example.rindus.service;

import com.example.rindus.TestUtils;
import com.example.rindus.entity.Todo;
import com.example.rindus.exception.ResourceFormatException;
import com.example.rindus.model.TodoRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TodoServiceTest {

    @Mock
    WebClient webClient;

    @Mock
    TodoRequest request;

    @Mock
    WebClient.ResponseSpec response;

    @Mock
    Mono<List<Todo>> todos;

    @Mock
    Mono<Todo> todoMono;

    @Mock
    List<Todo> todoList;

    @Mock
    WebClient.Builder webClientBuilder;

    @Mock
    WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    WebClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    WebClient.RequestBodySpec requestBodySpec;

    @Mock
    Mono<ResponseEntity<Void>> monoResponseEntity;

    @Mock
    Todo todo;

    TodoService service;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(webClientBuilder.baseUrl(any(String.class))).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);
        service = new TodoService(webClientBuilder);
    }

    @Test
    public void shouldGetTodosAndReturnTodoList() throws IOException {

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/todos")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);
        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(any(ParameterizedTypeReference.class))).thenReturn(todos);
        when(todos.block()).thenReturn(todoList);

        List<Todo> response = service.getTodos(false, false);

        assertEquals(response, todoList);
    }

    @Test
    public void shouldPostTodoAndReturnCreatedTodo() throws UnexpectedException, ResourceFormatException {
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/todos")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(Todo.class)).thenReturn(todoMono);
        when(todoMono.block()).thenReturn(todo);

        Todo response = service.postTodo(TestUtils.createDummyTodoRequest());

        assertEquals(response, todo);
    }

    @Test
    public void shouldUpdateTodoAndReturnUpdatedTodo() throws UnexpectedException, ResourceFormatException {
        when(webClient.put()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/todos/1")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(Todo.class)).thenReturn(todoMono);
        when(todoMono.block()).thenReturn(todo);

        Todo response = service.putTodo(TestUtils.createDummyTodoRequest());

        assertEquals(response, todo);
    }

    @Test
    public void shouldPatchTodoAndReturnPatchedTodo() throws UnexpectedException, ResourceFormatException {
        when(webClient.patch()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/todos/1")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(Todo.class)).thenReturn(todoMono);
        when(todoMono.block()).thenReturn(todo);

        Todo response = service.patchTodo(TestUtils.createDummyTodoRequest());

        assertEquals(response, todo);
    }

    @Test
    public void shouldDeleteTodoAndReturnDeletedTodoId() throws UnexpectedException, ResourceFormatException {
        when(webClient.delete()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/todos/1")).thenReturn(requestBodySpec);
        when(requestBodySpec.accept(MediaType.APPLICATION_JSON)).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(Todo.class)).thenReturn(todoMono);
        when(todoMono.block()).thenReturn(todo);

        int response = service.deleteTodo(1);

        assertEquals(response, HttpStatus.OK.value());
    }

    @Test
    public void shouldTakeAnInvalidTodoRequestAndThrowResourceFormatException() {
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/todos")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(Todo.class)).thenReturn(todoMono);
        when(todoMono.block()).thenReturn(todo);

        ResourceFormatException exception = assertThrows(ResourceFormatException.class, () -> {
            service.postTodo(TestUtils.createInvalidTodoRequest());
        });

        String expectedMessage = "Todo is not valid: id: This field must be positive";

        assertEquals(expectedMessage, exception.getMessage());

    }
}
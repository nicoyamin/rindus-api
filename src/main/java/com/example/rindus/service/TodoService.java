package com.example.rindus.service;

import com.example.rindus.ApiConstants;
import com.example.rindus.ResourceExtractor;
import com.example.rindus.entity.Todo;
import com.example.rindus.model.TodoRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@Service
public class TodoService {
    private final WebClient webClient;

    public TodoService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(ApiConstants.BASE_URL).build();
    }

    public List<Todo> getTodos(boolean extractJson, boolean extractXml) throws IOException {

        Mono<List<Todo>> response = webClient.get()
                .uri(ApiConstants.Resources.TODOS.resource)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Todo>>() {});

        List<Todo> todos = response.block();

        if(extractJson) {
            ResourceExtractor.extractToJson(ApiConstants.Resources.TODOS.resource, response);
        }

        if(extractXml) {
            ResourceExtractor.extractToXml(ApiConstants.Resources.TODOS.resource,
                    response,
                    ApiConstants.Resources.TODOS.tagName);
        }

        return todos;
    }

    public Todo postTodo(TodoRequest request) {
        Mono<Todo> response = webClient.post()
                .uri(ApiConstants.Resources.TODOS.resource)
                .body(Mono.just(request), TodoRequest.class)
                .retrieve()
                .bodyToMono(Todo.class);

        return  response.block();
    }

    public Todo putTodo(TodoRequest request) {

        String todoId ="/" + request.getId();

        Mono<Todo> response = webClient.put()
                .uri(ApiConstants.Resources.TODOS.resource + todoId)
                .body(Mono.just(request), TodoRequest.class)
                .retrieve()
                .bodyToMono(Todo.class);

        return  response.block();
    }

    public Todo patchTodo(TodoRequest request) {

        String todoId ="/" + request.getId();

        Mono<Todo> response = webClient.patch()
                .uri(ApiConstants.Resources.TODOS.resource + todoId)
                .body(Mono.just(request), TodoRequest.class)
                .retrieve()
                .bodyToMono(Todo.class);

        return  response.block();
    }

    public int deleteTodo(int todoId) {

        WebClient.ResponseSpec response = webClient.delete()
                .uri(ApiConstants.Resources.TODOS.resource + "/" + todoId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();


        return response.toBodilessEntity().block().getStatusCodeValue();

    }
}

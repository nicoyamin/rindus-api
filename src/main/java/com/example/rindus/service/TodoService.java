package com.example.rindus.service;

import com.example.rindus.ApiConstants;
import com.example.rindus.ResourceExtractor;
import com.example.rindus.entity.Todo;
import com.example.rindus.entity.Todo;
import com.example.rindus.exception.ResourceFormatException;
import com.example.rindus.model.TodoRequest;
import com.example.rindus.model.TodoRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

@Service
public class TodoService {
    private final WebClient webClient;

    public TodoService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(ApiConstants.BASE_URL).build();
    }

    public List<Todo> getTodos(boolean extractJson, boolean extractXml) throws IOException {

        WebClient.ResponseSpec response = webClient.get()
                .uri(ApiConstants.Resources.TODOS.resource)
                .retrieve();

        ApiConstants.checkForException(response.toBodilessEntity().block());

        Mono<List<Todo>> todos = response.bodyToMono(new ParameterizedTypeReference<List<Todo>>() {});

        if(extractJson) {
            ResourceExtractor.extractToJson(ApiConstants.Resources.TODOS.resource, todos);
        }

        if(extractXml) {
            ResourceExtractor.extractToXml(ApiConstants.Resources.TODOS.resource,
                    todos,
                    ApiConstants.Resources.TODOS.tagName);
        }

        return todos.block();
    }

    public Todo postTodo(TodoRequest request) throws UnexpectedException, ResourceFormatException {
        WebClient.ResponseSpec response  = webClient.post()
                .uri(ApiConstants.Resources.TODOS.resource)
                .body(Mono.just(createTodo(request)), Todo.class)
                .retrieve();

        ApiConstants.checkForException(response.toBodilessEntity().block());

        return response.bodyToMono(Todo.class).block();
    }

    public Todo putTodo(TodoRequest request) throws UnexpectedException, ResourceFormatException {

        String todoId ="/" + request.getId();

        WebClient.ResponseSpec response  = webClient.put()
                .uri(ApiConstants.Resources.TODOS.resource + todoId)
                .body(Mono.just(createTodo(request)), Todo.class)
                .retrieve();

        ApiConstants.checkForException(response.toBodilessEntity().block());

        return response.bodyToMono(Todo.class).block();
    }

    public Todo patchTodo(TodoRequest request) throws UnexpectedException, ResourceFormatException {

        String todoId ="/" + request.getId();

        WebClient.ResponseSpec response  = webClient.patch()
                .uri(ApiConstants.Resources.TODOS.resource + todoId)
                .body(Mono.just(createTodo(request)), Todo.class)
                .retrieve();

        ApiConstants.checkForException(response.toBodilessEntity().block());

        return response.bodyToMono(Todo.class).block();
    }

    public int deleteTodo(int todoId) throws UnexpectedException {

        WebClient.ResponseSpec response = webClient.delete()
                .uri(ApiConstants.Resources.TODOS.resource + "/" + todoId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();

        ApiConstants.checkForException(response.toBodilessEntity().block());


        return response.toBodilessEntity().block().getStatusCodeValue();

    }

    private Todo createTodo(TodoRequest request) throws ResourceFormatException {

        Todo newTodo = new Todo();

        newTodo.setId(request.getId());
        newTodo.setUserId(request.getUserId());
        newTodo.setTitle(request.getTitle());
        newTodo.setCompleted(request.getCompleted());

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<Todo>> constraintViolations = validator.validate(newTodo);

        if (constraintViolations.size() > 0) {
            StringJoiner joiner = new StringJoiner(", ","Todo is not valid: ", "");
            Set<String> violationMessages = new HashSet<String>();

            for (ConstraintViolation<Todo> constraintViolation : constraintViolations) {
                joiner.add(constraintViolation.getPropertyPath() +": "+constraintViolation.getMessage());
            }

            throw new ResourceFormatException(joiner.toString());
        }

        return newTodo;
    }
}

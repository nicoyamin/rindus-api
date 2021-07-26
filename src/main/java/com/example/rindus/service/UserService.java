package com.example.rindus.service;

import com.example.rindus.ApiConstants;
import com.example.rindus.entity.User;
import com.example.rindus.model.UserRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
public class UserService {


    private final WebClient webClient;

    public UserService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(ApiConstants.BASE_URL).build();
    }

    public List<User> getUsers() {

        Mono<List<User>> response = webClient.get()
                .uri(ApiConstants.Resources.USERS.resource)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<User>>() {});

        List<User> users = response.block();

        return users;
    }

    public User postUser(UserRequest request) {
        Mono<User> response = webClient.post()
                .uri(ApiConstants.Resources.USERS.resource)
                .body(Mono.just(request), UserRequest.class)
                .retrieve()
                .bodyToMono(User.class);

        return  response.block();
    }

    public User putUser(UserRequest request) {

        String userId ="/" + request.getId();

        Mono<User> response = webClient.put()
                .uri(ApiConstants.Resources.USERS.resource + userId)
                .body(Mono.just(request), UserRequest.class)
                .retrieve()
                .bodyToMono(User.class);

        return  response.block();
    }

    public User patchUser(UserRequest request) {

        String userId ="/" + request.getId();

        Mono<User> response = webClient.patch()
                .uri(ApiConstants.Resources.USERS.resource + userId)
                .body(Mono.just(request), UserRequest.class)
                .retrieve()
                .bodyToMono(User.class);

        return  response.block();
    }
}

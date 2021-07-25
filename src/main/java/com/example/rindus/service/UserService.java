package com.example.rindus.service;

import com.example.rindus.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
public class UserService {

    @Value("${mockApi.url}")
    private String baseUrl;

    private final WebClient webClient;

    public UserService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    public List<User> getUsers() {

        Mono<List<User>> response = webClient.get()
                .uri("/users")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<User>>() {});

        List<User> users = response.block();

        return users;
    }
}

package com.example.rindus.service;

import com.example.rindus.ApiConstants;
import com.example.rindus.ResourceExtractor;
import com.example.rindus.entity.User;
import com.example.rindus.entity.User;
import com.example.rindus.exception.ResourceFormatException;
import com.example.rindus.model.UserRequest;
import com.example.rindus.model.UserRequest;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;


@Service
public class UserService {


    private final WebClient webClient;

    public UserService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(ApiConstants.BASE_URL).build();
    }

    public List<User> getUsers(boolean extractJson, boolean extractXml) throws IOException {

        WebClient.ResponseSpec response = webClient.get()
                .uri(ApiConstants.Resources.USERS.resource)
                .retrieve();

        ApiConstants.checkForException(response.toBodilessEntity().block());

        Mono<List<User>> users = response.bodyToMono(new ParameterizedTypeReference<List<User>>() {});

        if(extractJson) {
            ResourceExtractor.extractToJson(ApiConstants.Resources.USERS.resource, users);
        }

        if(extractXml) {
            ResourceExtractor.extractToXml(ApiConstants.Resources.USERS.resource,
                    users,
                    ApiConstants.Resources.USERS.tagName);
        }

        return users.block();
    }

    public User postUser(UserRequest request) throws ResourceFormatException, UnexpectedException {
        WebClient.ResponseSpec response  = webClient.post()
                .uri(ApiConstants.Resources.USERS.resource)
                .body(Mono.just(createUser(request)), User.class)
                .retrieve();

        ApiConstants.checkForException(response.toBodilessEntity().block());

        return response.bodyToMono(User.class).block();
    }

    public User putUser(UserRequest request) throws ResourceFormatException, UnexpectedException {

        String userId ="/" + request.getId();

        WebClient.ResponseSpec response  = webClient.put()
                .uri(ApiConstants.Resources.USERS.resource + userId)
                .body(Mono.just(createUser(request)), User.class)
                .retrieve();

        ApiConstants.checkForException(response.toBodilessEntity().block());

        return response.bodyToMono(User.class).block();
    }

    public User patchUser(UserRequest request) throws ResourceFormatException, UnexpectedException {

        String userId ="/" + request.getId();

        WebClient.ResponseSpec response  = webClient.patch()
                .uri(ApiConstants.Resources.USERS.resource + userId)
                .body(Mono.just(createUser(request)), User.class)
                .retrieve();

        ApiConstants.checkForException(response.toBodilessEntity().block());

        return response.bodyToMono(User.class).block();
    }

    public int deleteUser(int userId) throws UnexpectedException {

        WebClient.ResponseSpec response = webClient.delete()
                .uri(ApiConstants.Resources.USERS.resource + "/" + userId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();

        ApiConstants.checkForException(response.toBodilessEntity().block());


        return response.toBodilessEntity().block().getStatusCodeValue();
    }

    private User createUser(UserRequest request) throws ResourceFormatException {

        User newUser = new User();

        newUser.setId(request.getId());
        newUser.setName(request.getName());
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        newUser.setAddress(request.getAddress());
        newUser.setPhone((request.getPhone()));
        newUser.setWebsite(request.getWebsite());
        newUser.setCompany((request.getCompany()));

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(newUser);

        if (constraintViolations.size() > 0) {
            StringJoiner joiner = new StringJoiner(", ","User is not valid: ", "");
            Set<String> violationMessages = new HashSet<String>();

            for (ConstraintViolation<User> constraintViolation : constraintViolations) {
                joiner.add(constraintViolation.getPropertyPath() +": "+constraintViolation.getMessage());
            }

            throw new ResourceFormatException(joiner.toString());
        }

        return newUser;
    }
}

package com.example.rindus.service;

import com.example.rindus.TestUtils;
import com.example.rindus.entity.User;
import com.example.rindus.exception.ResourceFormatException;
import com.example.rindus.model.UserRequest;
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

class UserServiceTest {

    @Mock
    WebClient webClient;

    @Mock
    UserRequest request;

    @Mock
    WebClient.ResponseSpec response;

    @Mock
    Mono<List<User>> users;

    @Mock
    Mono<User> userMono;

    @Mock
    List<User> userList;

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
    User user;

    UserService service;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(webClientBuilder.baseUrl(any(String.class))).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);
        service = new UserService(webClientBuilder);
    }

    @Test
    public void shouldGetUsersAndReturnUserList() throws IOException {

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/users")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);
        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(any(ParameterizedTypeReference.class))).thenReturn(users);
        when(users.block()).thenReturn(userList);

        List<User> response = service.getUsers(false, false);

        assertEquals(response, userList);
    }

    @Test
    public void shouldPostUserAndReturnCreatedUser() throws UnexpectedException, ResourceFormatException {
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/users")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(User.class)).thenReturn(userMono);
        when(userMono.block()).thenReturn(user);

        User response = service.postUser(TestUtils.createDummyUserRequest());

        assertEquals(response, user);
    }

    @Test
    public void shouldUpdateUserAndReturnUpdatedUser() throws UnexpectedException, ResourceFormatException {
        when(webClient.put()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/users/1")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(User.class)).thenReturn(userMono);
        when(userMono.block()).thenReturn(user);

        User response = service.putUser(TestUtils.createDummyUserRequest());

        assertEquals(response, user);
    }

    @Test
    public void shouldPatchUserAndReturnPatchedUser() throws UnexpectedException, ResourceFormatException {
        when(webClient.patch()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/users/1")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(User.class)).thenReturn(userMono);
        when(userMono.block()).thenReturn(user);

        User response = service.patchUser(TestUtils.createDummyUserRequest());

        assertEquals(response, user);
    }

    @Test
    public void shouldDeleteUserAndReturnDeletedUserId() throws UnexpectedException, ResourceFormatException {
        when(webClient.delete()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/users/1")).thenReturn(requestBodySpec);
        when(requestBodySpec.accept(MediaType.APPLICATION_JSON)).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(User.class)).thenReturn(userMono);
        when(userMono.block()).thenReturn(user);

        int response = service.deleteUser(1);

        assertEquals(response, HttpStatus.OK.value());
    }

    @Test
    public void shouldTakeAnInvalidUserRequestAndThrowResourceFormatException() {
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/users")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(User.class)).thenReturn(userMono);
        when(userMono.block()).thenReturn(user);

        ResourceFormatException exception = assertThrows(ResourceFormatException.class, () -> {
            service.postUser(TestUtils.createInvalidUserRequest());
        });

        String expectedMessage = "User is not valid: id: This field must be positive";

        assertEquals(expectedMessage, exception.getMessage());

    }
}
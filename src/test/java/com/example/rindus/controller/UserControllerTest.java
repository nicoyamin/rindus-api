package com.example.rindus.controller;

import com.example.rindus.entity.User;
import com.example.rindus.exception.ResourceFormatException;
import com.example.rindus.model.UserRequest;
import com.example.rindus.service.UserService;
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

public class UserControllerTest {
    @Mock
    UserService service;

    @Mock
    UserRequest request;

    @Mock
    User user;

    UserController controller;

    @Mock
    List<User> users;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller= new UserController(service);
    }

    @Test
    public void shouldGetUsersAndReturnUserListAnd200Response() throws IOException {


        when(service.getUsers(any(Boolean.class), any(Boolean.class))).thenReturn(users);

        ResponseEntity response = controller.getUsers(false, false);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), users);
    }

    @Test
    public void shouldCreateUserAndReturnCreatedUserAnd200Response() throws IOException, ResourceFormatException {


        when(service.postUser(request)).thenReturn(user);

        ResponseEntity response = controller.postUser(request);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), user);
    }

    @Test
    public void shouldUpdateUserAndReturnUpdatedUserAnd200Response() throws IOException, ResourceFormatException {


        when(service.putUser(request)).thenReturn(user);

        ResponseEntity response = controller.putUser(request);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), user);
    }

    @Test
    public void shouldPatchUserAndReturnPatchedUserAnd200Response() throws IOException, ResourceFormatException {


        when(service.patchUser(request)).thenReturn(user);

        ResponseEntity response = controller.patchUser(request);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), user);
    }

    @Test
    public void shouldDeleteUserAndReturnDeletedUserIdAnd200Response() throws IOException, ResourceFormatException {


        when(service.deleteUser(1)).thenReturn(1);

        ResponseEntity response = controller.deleteUser(1);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), 1);
    }
}

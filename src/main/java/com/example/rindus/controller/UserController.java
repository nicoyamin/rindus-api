package com.example.rindus.controller;

import com.example.rindus.entity.User;
import com.example.rindus.exception.ResourceFormatException;
import com.example.rindus.model.UserRequest;
import com.example.rindus.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.rmi.UnexpectedException;
import java.util.List;

@RestController
public class UserController implements UserApi{

    private UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @SneakyThrows
    @Override
    public ResponseEntity <List<User>> getUsers(boolean extractJson, boolean extractXml) {
        List<User> users = userService.getUsers(extractJson, extractXml);
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<User> postUser(@Valid UserRequest request) throws UnexpectedException, ResourceFormatException {
        User createdUser = userService.postUser(request);
        return ResponseEntity.ok(createdUser);
    }

    @Override
    public ResponseEntity<User> putUser(@Valid UserRequest request) throws UnexpectedException, ResourceFormatException {
        User updatedUser = userService.putUser(request);
        return ResponseEntity.ok(updatedUser);
    }

    @Override
    public ResponseEntity<User> patchUser(@Valid UserRequest request) throws UnexpectedException, ResourceFormatException {

        User updatedUser = userService.patchUser(request);
        return ResponseEntity.ok(updatedUser);
    }

    @Override
    public ResponseEntity deleteUser(int userId) throws UnexpectedException {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User with Id " + userId + " deleted successfully");
    }
}

package com.example.rindus.controller;

import com.example.rindus.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController implements UserApi{

    private UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<Boolean> isMutant(@Valid HumanRequest request) throws ResourceFormatException {


        boolean isMutant = humanService.isMutant(request.getDna());

            return ResponseEntity.ok(isMutant);


    }

    @Override
    public ResponseEntity<User> getUsers() {



        return null;
    }
}

package com.example.rindus.controller;

import com.example.rindus.entity.User;
import com.example.rindus.model.UserRequest;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Api(value="users")
public interface UserApi {
    @ApiOperation(value = "Get users", nickname = "getUsers", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 204, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "No user found"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="users", method= RequestMethod.GET)
    ResponseEntity<List<User>> getUsers();

    @ApiOperation(value = "Create a new user", nickname = "postUser", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "New User created successfully"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 204, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/users", produces={"application/JSON"}, method= RequestMethod.POST)
    ResponseEntity<User> postUser(@ApiParam(value="Data for new User")
                                  @Valid @RequestBody(required=true) UserRequest request);

    @ApiOperation(value = "Update user or create if not found", nickname = "putUser", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User found and updated successfully"),
            @ApiResponse(code = 201, message = "User not found, so it was created successfully"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 204, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/users", produces={"application/JSON"}, method= RequestMethod.PUT)
    ResponseEntity<User> putUser(@ApiParam(value="User Data with updated information")
                                  @Valid @RequestBody(required=true) UserRequest request);
}

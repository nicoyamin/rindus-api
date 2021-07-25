package com.example.rindus.controller;

import com.example.rindus.entity.User;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    /*@ApiOperation(value = "Check randomly generated DNA sequences", nickname = "testRandomDna", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The results are in"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 204, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Something went wrong"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/human/testRandomDna", method= RequestMethod.GET)
    ResponseEntity<String> testRandomDna(@ApiParam(value="Numbers of sequences to test")
                                         @Valid @RequestParam(required=true) int dnaAmount,
                                         @ApiParam(value="Size of DNA samples")
                                         @Valid @RequestParam(required=true) int dnaSize) throws ResourceFormatException;*/
}

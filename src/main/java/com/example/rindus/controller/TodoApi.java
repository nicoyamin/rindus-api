package com.example.rindus.controller;

import com.example.rindus.entity.Todo;
import com.example.rindus.exception.ResourceFormatException;
import com.example.rindus.model.TodoRequest;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.rmi.UnexpectedException;
import java.util.List;

@Api(value = "todos")
public interface TodoApi {
    @ApiOperation(value = "Get todos", nickname = "getTodos", response = Todo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "No todos found"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/todos", method= RequestMethod.GET)
    ResponseEntity<List<Todo>> getTodos(@ApiParam(value="Extract data to JSON file")
                                        @Valid @RequestParam(required=false, defaultValue = "false") boolean extractJson,
                                        @ApiParam(value="Extract data to XML file")
                                        @Valid @RequestParam(required=false, defaultValue = "false") boolean extractXml);

    @ApiOperation(value = "Create a new todo", nickname = "postTodo", response = Todo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "New Todo created successfully"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/todos", produces={"application/JSON"}, method= RequestMethod.POST)
    ResponseEntity<Todo> postTodo(@ApiParam(value="Data for new Todo")
                                        @Valid @RequestBody(required=true) TodoRequest request) throws UnexpectedException, ResourceFormatException;

    @ApiOperation(value = "Update todo or create if not found", nickname = "putTodo", response = Todo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Todo found and updated successfully"),
            @ApiResponse(code = 201, message = "Todo not found, so it was created successfully"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/todos", produces={"application/JSON"}, method= RequestMethod.PUT)
    ResponseEntity<Todo> putTodo(@ApiParam(value="Todo data with updated information")
                                       @Valid @RequestBody(required=true) TodoRequest request) throws UnexpectedException, ResourceFormatException;

    @ApiOperation(value = "Patch todo", nickname = "patchTodo", response = Todo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Todo updated successfully"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Todo not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/todos", produces={"application/JSON"}, method= RequestMethod.PATCH)
    ResponseEntity<Todo> patchTodo(@ApiParam(value="Todo data with updated information")
                                         @Valid @RequestBody(required=true) TodoRequest request) throws UnexpectedException, ResourceFormatException;


    @ApiOperation(value = "Delete todo", nickname = "deleteTodo", response = Todo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Todo deleted successfully"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Todo not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/todos", produces={"application/JSON"}, method= RequestMethod.DELETE)
    ResponseEntity deleteTodo(@ApiParam(value="Id of the todo to delete")
                                 @Valid @RequestParam(required=true) int todoId) throws UnexpectedException;
}

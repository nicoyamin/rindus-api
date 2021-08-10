package com.example.rindus.controller;

import com.example.rindus.entity.Post;
import com.example.rindus.entity.User;
import com.example.rindus.model.PostRequest;
import com.example.rindus.model.UserRequest;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Api(value="posts")
public interface PostApi {
    @ApiOperation(value = "Get posts", nickname = "getPosts", response = Post.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "No posts found"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/posts", method= RequestMethod.GET)
    ResponseEntity<List<Post>> getPosts(@ApiParam(value="Extract data to JSON file")
                                        @Valid @RequestParam(required=false, defaultValue = "false") boolean extractJson,
                                        @ApiParam(value="Extract data to XML file")
                                        @Valid @RequestParam(required=false, defaultValue = "false") boolean extractXml);

    @ApiOperation(value = "Create a new post", nickname = "newPost", response = Post.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "New Post created successfully"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/posts", produces={"application/JSON"}, method= RequestMethod.POST)
    ResponseEntity<Post> newPost(@ApiParam(value="Data for new Post")
                                  @Valid @RequestBody(required=true) PostRequest request);

    @ApiOperation(value = "Update post or create if not found", nickname = "putPost", response = Post.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Post found and updated successfully"),
            @ApiResponse(code = 201, message = "Post not found, so it was created successfully"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/posts", produces={"application/JSON"}, method= RequestMethod.PUT)
    ResponseEntity<Post> putPost(@ApiParam(value="Post Data with updated information")
                                 @Valid @RequestBody(required=true) PostRequest request);

    @ApiOperation(value = "Patch post", nickname = "patchPost", response = Post.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Post updated successfully"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/posts", produces={"application/JSON"}, method= RequestMethod.PATCH)
    ResponseEntity<Post> patchPost(@ApiParam(value="User Data with updated information")
                                   @Valid @RequestBody(required=true) PostRequest request);


    @ApiOperation(value = "Delete post", nickname = "deletePost", response = Post.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User deleted successfully"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/posts", produces={"application/JSON"}, method= RequestMethod.DELETE)
    ResponseEntity deletePost(@ApiParam(value="Id of the post to delete")
                              @Valid @RequestParam(required=true) int postId);
}

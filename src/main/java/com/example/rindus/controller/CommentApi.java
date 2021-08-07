package com.example.rindus.controller;

import com.example.rindus.entity.Comment;
import com.example.rindus.entity.Post;
import com.example.rindus.model.CommentsRequest;
import com.example.rindus.model.PostRequest;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Api(value="comments")
public interface CommentApi {
    @ApiOperation(value = "Get comments", nickname = "getComments", response = Comment.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 204, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "No comments found"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/comments", method= RequestMethod.GET)
    ResponseEntity<List<Comment>> getComments(@ApiParam(value="Extract data to JSON file")
                                              @Valid @RequestParam(required=false) boolean extractJson,
                                              @ApiParam(value="Extract data to XML file")
                                              @Valid @RequestParam(required=false) boolean extractXml);

    @ApiOperation(value = "Create a new comment", nickname = "postComment", response = Comment.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "New Comment created successfully"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 204, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/comments", produces={"application/JSON"}, method= RequestMethod.POST)
    ResponseEntity<Comment> postComment(@ApiParam(value="Data for new Comment")
                                 @Valid @RequestBody(required=true) CommentsRequest request);

    @ApiOperation(value = "Update comment or create if not found", nickname = "putComment", response = Comment.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Comment found and updated successfully"),
            @ApiResponse(code = 201, message = "Comment not found, so it was created successfully"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 204, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/comments", produces={"application/JSON"}, method= RequestMethod.PUT)
    ResponseEntity<Comment> putComment(@ApiParam(value="Comment data with updated information")
                                 @Valid @RequestBody(required=true) CommentsRequest request);

    @ApiOperation(value = "Patch comment", nickname = "patchComment", response = Comment.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Comment updated successfully"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 204, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Comment not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/comments", produces={"application/JSON"}, method= RequestMethod.PATCH)
    ResponseEntity<Comment> patchComment(@ApiParam(value="Comment data with updated information")
                                   @Valid @RequestBody(required=true) CommentsRequest request);


    @ApiOperation(value = "Delete comment", nickname = "deleteComment", response = Comment.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Comment deleted successfully"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 204, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Comment not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/comments", produces={"application/JSON"}, method= RequestMethod.DELETE)
    ResponseEntity deleteComment(@ApiParam(value="Id of the comment to delete")
                              @Valid @RequestParam(required=true) int commentId);
}

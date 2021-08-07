package com.example.rindus.controller;

import com.example.rindus.entity.Comment;
import com.example.rindus.entity.User;
import com.example.rindus.model.CommentsRequest;
import com.example.rindus.service.CommentService;
import com.example.rindus.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class CommentController implements CommentApi {

    private CommentService commentService;


    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @SneakyThrows
    @Override
    public ResponseEntity<List<Comment>> getComments(boolean extractJson, boolean extractXml) {
        List<Comment> comments = commentService.getComments(extractJson, extractXml);
        return ResponseEntity.ok(comments);
    }

    @Override
    public ResponseEntity<Comment> postComment(@Valid CommentsRequest request) {
        Comment createdComment = commentService.postComment(request);
        return ResponseEntity.ok(createdComment);
    }

    @Override
    public ResponseEntity<Comment> putComment(@Valid CommentsRequest request) {
        Comment updatedComment = commentService.putComment(request);
        return ResponseEntity.ok(updatedComment);
    }

    @Override
    public ResponseEntity<Comment> patchComment(@Valid CommentsRequest request) {
        Comment updatedComment = commentService.patchComment(request);
        return ResponseEntity.ok(updatedComment);
    }

    @Override
    public ResponseEntity deleteComment(@Valid int commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("Comment with Id " + commentId + " deleted successfully");    }
}

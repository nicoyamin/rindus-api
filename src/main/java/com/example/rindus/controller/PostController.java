package com.example.rindus.controller;

import com.example.rindus.entity.Post;
import com.example.rindus.exception.ResourceFormatException;
import com.example.rindus.model.PostRequest;
import com.example.rindus.service.PostService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.rmi.UnexpectedException;
import java.util.List;

@RestController
public class PostController implements PostApi{
    private PostService postService;


    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @SneakyThrows
    @Override
    public ResponseEntity<List<Post>> getPosts(boolean extractJson, boolean extractXml) {
        List<Post> posts = postService.getPosts(extractJson, extractXml);
        return ResponseEntity.ok(posts);
    }

    @Override
    public ResponseEntity<Post> newPost(@Valid PostRequest request) throws UnexpectedException, ResourceFormatException {
        Post createdPost = postService.newPost(request);
        return ResponseEntity.ok(createdPost);
    }

    @Override
    public ResponseEntity<Post> putPost(@Valid PostRequest request) throws UnexpectedException, ResourceFormatException {
        Post updatedPost = postService.putPost(request);
        return ResponseEntity.ok(updatedPost);
    }

    @Override
    public ResponseEntity<Post> patchPost(@Valid PostRequest request) throws UnexpectedException, ResourceFormatException {

        Post updatedPost = postService.patchPost(request);
        return ResponseEntity.ok(updatedPost);
    }

    @Override
    public ResponseEntity deletePost(int postId) throws UnexpectedException {
        postService.deletePost(postId);
        return ResponseEntity.ok("Post with Id " + postId + " deleted successfully");
    }
}

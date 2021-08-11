package com.example.rindus.controller;

import com.example.rindus.entity.Post;
import com.example.rindus.exception.ResourceFormatException;
import com.example.rindus.model.PostRequest;
import com.example.rindus.service.PostService;
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

public class PostControllerTest {

    @Mock
    PostService service;

    @Mock
    PostRequest request;

    @Mock
    Post post;

    PostController controller;

    @Mock
    List<Post> posts;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller= new PostController(service);
    }

    @Test
    public void shouldGetPostsAndReturnPostListAnd200Response() throws IOException {


        when(service.getPosts(any(Boolean.class), any(Boolean.class))).thenReturn(posts);

        ResponseEntity response = controller.getPosts(false, false);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), posts);
    }

    @Test
    public void shouldCreatePostAndReturnCreatedPostAnd200Response() throws IOException, ResourceFormatException {


        when(service.newPost(request)).thenReturn(post);

        ResponseEntity response = controller.newPost(request);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), post);
    }

    @Test
    public void shouldUpdatePostAndReturnUpdatedPostAnd200Response() throws IOException, ResourceFormatException {


        when(service.putPost(request)).thenReturn(post);

        ResponseEntity response = controller.putPost(request);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), post);
    }

    @Test
    public void shouldPatchPostAndReturnPatchedPostAnd200Response() throws IOException, ResourceFormatException {


        when(service.patchPost(request)).thenReturn(post);

        ResponseEntity response = controller.patchPost(request);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), post);
    }

    @Test
    public void shouldDeletePostAndReturnDeletedPostIdAnd200Response() throws IOException, ResourceFormatException {


        when(service.deletePost(1)).thenReturn(1);

        ResponseEntity response = controller.deletePost(1);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), 1);
    }
}

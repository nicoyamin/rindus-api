package com.example.rindus.controller;

import com.example.rindus.TestUtils;
import com.example.rindus.entity.Comment;
import com.example.rindus.exception.ResourceFormatException;
import com.example.rindus.model.CommentsRequest;
import com.example.rindus.service.CommentService;
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

public class CommentControllerTest {

    @Mock
    CommentService service;

    @Mock
    CommentsRequest request;

    @Mock
    Comment comment;

    CommentController controller;

    @Mock
    List<Comment> comments;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller= new CommentController(service);
    }

    @Test
    public void shouldGetCommentsAndReturnCommentListAnd200Response() throws IOException {


        when(service.getComments(any(Boolean.class), any(Boolean.class))).thenReturn(comments);

        ResponseEntity response = controller.getComments(false, false);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), comments);
    }

    @Test
    public void shouldCreateCommentAndReturnCreatedCommentAnd200Response() throws IOException, ResourceFormatException {


        when(service.postComment(request)).thenReturn(comment);

        ResponseEntity response = controller.postComment(request);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), comment);
    }

    @Test
    public void shouldUpdateCommentAndReturnUpdatedCommentAnd200Response() throws IOException, ResourceFormatException {


        when(service.putComment(request)).thenReturn(comment);

        ResponseEntity response = controller.putComment(request);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), comment);
    }

    @Test
    public void shouldPatchCommentAndReturnPatchedCommentAnd200Response() throws IOException, ResourceFormatException {


        when(service.patchComment(request)).thenReturn(comment);

        ResponseEntity response = controller.patchComment(request);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), comment);
    }

    @Test
    public void shouldDeleteCommentAndReturnDeletedCommentIdAnd200Response() throws IOException, ResourceFormatException {


        when(service.deleteComment(1)).thenReturn(1);

        ResponseEntity response = controller.deleteComment(1);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), 1);
    }
}

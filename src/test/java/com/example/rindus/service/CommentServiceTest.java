package com.example.rindus.service;

import com.example.rindus.TestUtils;
import com.example.rindus.entity.Comment;
import com.example.rindus.exception.ResourceFormatException;
import com.example.rindus.model.CommentsRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CommentServiceTest {

    @Mock
    WebClient webClient;

    @Mock
    CommentsRequest request;

    @Mock
    WebClient.ResponseSpec response;

    @Mock
    Mono<List<Comment>> comments;

    @Mock
    Mono<Comment> commentMono;

    @Mock
    List<Comment> commentList;

    @Mock
    WebClient.Builder webClientBuilder;

    @Mock
    WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    WebClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    WebClient.RequestBodySpec requestBodySpec;

    @Mock
    Mono<ResponseEntity<Void>> monoResponseEntity;

    @Mock
    Comment comment;

    CommentService service;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(webClientBuilder.baseUrl(any(String.class))).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);
        service = new CommentService(webClientBuilder);
    }

    @Test
    public void shouldGetCommentsAndReturnCommentList() throws IOException {

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/comments")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);
        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(any(ParameterizedTypeReference.class))).thenReturn(comments);
        when(comments.block()).thenReturn(commentList);

        List<Comment> response = service.getComments(false, false);

        assertEquals(response, commentList);
    }

    @Test
    public void shouldPostCommentAndReturnCreatedComment() throws UnexpectedException, ResourceFormatException {
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/comments")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(Comment.class)).thenReturn(commentMono);
        when(commentMono.block()).thenReturn(comment);

        Comment response = service.postComment(TestUtils.createDummyCommentRequest());

        assertEquals(response, comment);
    }

    @Test
    public void shouldUpdateCommentAndReturnUpdatedComment() throws UnexpectedException, ResourceFormatException {
        when(webClient.put()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/comments/1")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(Comment.class)).thenReturn(commentMono);
        when(commentMono.block()).thenReturn(comment);

        Comment response = service.putComment(TestUtils.createDummyCommentRequest());

        assertEquals(response, comment);
    }

    @Test
    public void shouldPatchCommentAndReturnPatchedComment() throws UnexpectedException, ResourceFormatException {
        when(webClient.patch()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/comments/1")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(Comment.class)).thenReturn(commentMono);
        when(commentMono.block()).thenReturn(comment);

        Comment response = service.patchComment(TestUtils.createDummyCommentRequest());

        assertEquals(response, comment);
    }

    @Test
    public void shouldDeleteCommentAndReturnDeletedCommentId() throws UnexpectedException, ResourceFormatException {
        when(webClient.delete()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/comments/1")).thenReturn(requestBodySpec);
        when(requestBodySpec.accept(MediaType.APPLICATION_JSON)).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(Comment.class)).thenReturn(commentMono);
        when(commentMono.block()).thenReturn(comment);

        int response = service.deleteComment(1);

        assertEquals(response, HttpStatus.OK.value());
    }

    @Test
    public void shouldTakeAnInvalidCommentRequestAndThrowResourceFormatException() {
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/comments")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(Comment.class)).thenReturn(commentMono);
        when(commentMono.block()).thenReturn(comment);

        ResourceFormatException exception = assertThrows(ResourceFormatException.class, () -> {
            service.postComment(TestUtils.createInvalidCommentRequest());
        });

        String expectedMessage = "Comment is not valid: email: Invalid email format";

        assertEquals(expectedMessage, exception.getMessage());

    }
}
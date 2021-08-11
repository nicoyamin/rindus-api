package com.example.rindus.service;

import com.example.rindus.TestUtils;
import com.example.rindus.entity.Post;
import com.example.rindus.exception.ResourceFormatException;
import com.example.rindus.model.PostRequest;
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

class PostServiceTest {

    @Mock
    WebClient webClient;

    @Mock
    PostRequest request;

    @Mock
    WebClient.ResponseSpec response;

    @Mock
    Mono<List<Post>> posts;

    @Mock
    Mono<Post> postMono;

    @Mock
    List<Post> postList;

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
    Post post;

    PostService service;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(webClientBuilder.baseUrl(any(String.class))).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);
        service = new PostService(webClientBuilder);
    }

    @Test
    public void shouldGetPostsAndReturnPostList() throws IOException {

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/posts")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);
        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(any(ParameterizedTypeReference.class))).thenReturn(posts);
        when(posts.block()).thenReturn(postList);

        List<Post> response = service.getPosts(false, false);

        assertEquals(response, postList);
    }

    @Test
    public void shouldPostPostAndReturnCreatedPost() throws UnexpectedException, ResourceFormatException {
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/posts")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(Post.class)).thenReturn(postMono);
        when(postMono.block()).thenReturn(post);

        Post response = service.newPost(TestUtils.createDummyPostRequest());

        assertEquals(response, post);
    }

    @Test
    public void shouldUpdatePostAndReturnUpdatedPost() throws UnexpectedException, ResourceFormatException {
        when(webClient.put()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/posts/1")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(Post.class)).thenReturn(postMono);
        when(postMono.block()).thenReturn(post);

        Post response = service.putPost(TestUtils.createDummyPostRequest());

        assertEquals(response, post);
    }

    @Test
    public void shouldPatchPostAndReturnPatchedPost() throws UnexpectedException, ResourceFormatException {
        when(webClient.patch()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/posts/1")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(Post.class)).thenReturn(postMono);
        when(postMono.block()).thenReturn(post);

        Post response = service.patchPost(TestUtils.createDummyPostRequest());

        assertEquals(response, post);
    }

    @Test
    public void shouldDeletePostAndReturnDeletedPostId() throws UnexpectedException, ResourceFormatException {
        when(webClient.delete()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/posts/1")).thenReturn(requestBodySpec);
        when(requestBodySpec.accept(MediaType.APPLICATION_JSON)).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(Post.class)).thenReturn(postMono);
        when(postMono.block()).thenReturn(post);

        int response = service.deletePost(1);

        assertEquals(response, HttpStatus.OK.value());
    }

    @Test
    public void shouldTakeAnInvalidPostRequestAndThrowResourceFormatException() {
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/posts")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(Post.class)).thenReturn(postMono);
        when(postMono.block()).thenReturn(post);

        ResourceFormatException exception = assertThrows(ResourceFormatException.class, () -> {
            service.newPost(TestUtils.createInvalidPostRequest());
        });

        String expectedMessage = "Post is not valid: id: This field must be positive";

        assertEquals(expectedMessage, exception.getMessage());

    }
}
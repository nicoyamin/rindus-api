package com.example.rindus.service;

import com.example.rindus.ApiConstants;
import com.example.rindus.ResourceExtractor;
import com.example.rindus.entity.Post;
import com.example.rindus.entity.Post;
import com.example.rindus.exception.ResourceFormatException;
import com.example.rindus.model.PostRequest;
import com.example.rindus.model.PostRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

@Service
public class PostService {
    private final WebClient webClient;

    public PostService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(ApiConstants.BASE_URL).build();
    }

    public List<Post> getPosts(boolean extractJson, boolean extractXml) throws IOException {

        WebClient.ResponseSpec response = webClient.get()
                .uri(ApiConstants.Resources.POSTS.resource)
                .retrieve();

        ApiConstants.checkForException(response.toBodilessEntity().block());

        Mono<List<Post>> posts = response.bodyToMono(new ParameterizedTypeReference<List<Post>>() {});

        if(extractJson) {
            ResourceExtractor.extractToJson(ApiConstants.Resources.POSTS.resource, posts);
        }

        if(extractXml) {
            ResourceExtractor.extractToXml(ApiConstants.Resources.POSTS.resource,
                    posts,
                    ApiConstants.Resources.POSTS.tagName);
        }

        return posts.block();
    }

    public Post newPost(PostRequest request) throws ResourceFormatException, UnexpectedException {
        WebClient.ResponseSpec response  = webClient.post()
                .uri(ApiConstants.Resources.POSTS.resource)
                .body(Mono.just(createPost(request)), Post.class)
                .retrieve();

        ApiConstants.checkForException(response.toBodilessEntity().block());

        return response.bodyToMono(Post.class).block();
    }

    public Post putPost(PostRequest request) throws ResourceFormatException, UnexpectedException {

        String postId ="/" + request.getId();

        WebClient.ResponseSpec response  = webClient.put()
                .uri(ApiConstants.Resources.POSTS.resource + postId)
                .body(Mono.just(createPost(request)), Post.class)
                .retrieve();

        ApiConstants.checkForException(response.toBodilessEntity().block());

        return response.bodyToMono(Post.class).block();
    }

    public Post patchPost(PostRequest request) throws ResourceFormatException, UnexpectedException {
        String postId ="/" + request.getId();

        WebClient.ResponseSpec response  = webClient.patch()
                .uri(ApiConstants.Resources.POSTS.resource + postId)
                .body(Mono.just(createPost(request)), Post.class)
                .retrieve();

        ApiConstants.checkForException(response.toBodilessEntity().block());

        return response.bodyToMono(Post.class).block();
    }

    public int deletePost(int postId) throws UnexpectedException {

        WebClient.ResponseSpec response = webClient.delete()
                .uri(ApiConstants.Resources.POSTS.resource + "/" + postId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();

        ApiConstants.checkForException(response.toBodilessEntity().block());


        return response.toBodilessEntity().block().getStatusCodeValue();

    }

    private Post createPost(PostRequest request) throws ResourceFormatException {

        Post newPost = new Post();

        newPost.setId(request.getId());
        newPost.setUserId(request.getUserId());
        newPost.setTitle(request.getTitle());
        newPost.setBody(request.getBody());

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<Post>> constraintViolations = validator.validate(newPost);

        if (constraintViolations.size() > 0) {
            StringJoiner joiner = new StringJoiner(", ","Post is not valid: ", "");
            Set<String> violationMessages = new HashSet<String>();

            for (ConstraintViolation<Post> constraintViolation : constraintViolations) {
                joiner.add(constraintViolation.getPropertyPath() +": "+constraintViolation.getMessage());
            }

            throw new ResourceFormatException(joiner.toString());
        }

        return newPost;
    }
}

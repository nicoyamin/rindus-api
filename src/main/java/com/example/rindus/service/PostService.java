package com.example.rindus.service;

import com.example.rindus.ApiConstants;
import com.example.rindus.ResourceExtractor;
import com.example.rindus.entity.Post;
import com.example.rindus.model.PostRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@Service
public class PostService {
    private final WebClient webClient;

    public PostService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(ApiConstants.BASE_URL).build();
    }

    public List<Post> getPosts(boolean extractJson, boolean extractXml) throws IOException {

        Mono<List<Post>> response = webClient.get()
                .uri(ApiConstants.Resources.POSTS.resource)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Post>>() {});

        List<Post> posts = response.block();

        if(extractJson) {
            ResourceExtractor.extractToJson(ApiConstants.Resources.POSTS.resource, response);
        }

        if(extractXml) {
            ResourceExtractor.extractToXml(ApiConstants.Resources.POSTS.resource,
                    response,
                    ApiConstants.Resources.POSTS.tagName);
        }

        return posts;
    }

    public Post newPost(PostRequest request) {
        Mono<Post> response = webClient.post()
                .uri(ApiConstants.Resources.POSTS.resource)
                .body(Mono.just(request), PostRequest.class)
                .retrieve()
                .bodyToMono(Post.class);

        return  response.block();
    }

    public Post putPost(PostRequest request) {

        String postId ="/" + request.getId();

        Mono<Post> response = webClient.put()
                .uri(ApiConstants.Resources.POSTS.resource + postId)
                .body(Mono.just(request), PostRequest.class)
                .retrieve()
                .bodyToMono(Post.class);

        return  response.block();
    }

    public Post patchPost(PostRequest request) {

        String postId ="/" + request.getId();

        Mono<Post> response = webClient.patch()
                .uri(ApiConstants.Resources.POSTS.resource + postId)
                .body(Mono.just(request), PostRequest.class)
                .retrieve()
                .bodyToMono(Post.class);

        return  response.block();
    }

    public int deletePost(int postId) {

       WebClient.ResponseSpec response = webClient.delete()
                .uri(ApiConstants.Resources.POSTS.resource + "/" + postId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();


        return response.toBodilessEntity().block().getStatusCodeValue();

    }
}

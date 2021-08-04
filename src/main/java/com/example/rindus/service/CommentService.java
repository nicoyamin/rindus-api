package com.example.rindus.service;

import com.example.rindus.ApiConstants;
import com.example.rindus.ResourceExtractor;
import com.example.rindus.entity.Comment;
import com.example.rindus.entity.Post;
import com.example.rindus.model.CommentsRequest;
import com.example.rindus.model.PostRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@Service
public class CommentService {
    private final WebClient webClient;

    public CommentService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(ApiConstants.BASE_URL).build();
    }

    public List<Comment> getComments(boolean extractJson, boolean extractXml) throws IOException {

        Mono<List<Comment>> response = webClient.get()
                .uri(ApiConstants.Resources.COMMENTS.resource)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Comment>>() {});

        List<Comment> comments = response.block();

        if(extractJson) {
            ResourceExtractor.extractToJson(ApiConstants.Resources.COMMENTS.resource, response);
        }

        if(extractXml) {
            ResourceExtractor.extractToXml(ApiConstants.Resources.COMMENTS.resource,
                    response,
                    ApiConstants.Resources.COMMENTS.tagName);
        }

        return comments;
    }

    public Comment postComment(CommentsRequest request) {
        Mono<Comment> response = webClient.post()
                .uri(ApiConstants.Resources.COMMENTS.resource)
                .body(Mono.just(request), CommentsRequest.class)
                .retrieve()
                .bodyToMono(Comment.class);

        return  response.block();
    }

    public Comment putComment(CommentsRequest request) {

        String commentId ="/" + request.getId();

        Mono<Comment> response = webClient.put()
                .uri(ApiConstants.Resources.COMMENTS.resource + commentId)
                .body(Mono.just(request), CommentsRequest.class)
                .retrieve()
                .bodyToMono(Comment.class);

        return  response.block();
    }

    public Comment patchComment(CommentsRequest request) {

        String commentId ="/" + request.getId();

        Mono<Comment> response = webClient.patch()
                .uri(ApiConstants.Resources.COMMENTS.resource + commentId)
                .body(Mono.just(request), CommentsRequest.class)
                .retrieve()
                .bodyToMono(Comment.class);

        return  response.block();
    }

    public int deleteComment(int commentId) {

        WebClient.ResponseSpec response = webClient.delete()
                .uri(ApiConstants.Resources.COMMENTS.resource + "/" + commentId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();


        return response.toBodilessEntity().block().getStatusCodeValue();

    }
}

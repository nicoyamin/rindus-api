package com.example.rindus.service;

import com.example.rindus.ApiConstants;
import com.example.rindus.ResourceExtractor;
import com.example.rindus.entity.Comment;
import com.example.rindus.exception.ResourceFormatException;
import com.example.rindus.model.CommentsRequest;
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
public class CommentService {
    private final WebClient webClient;

    public CommentService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(ApiConstants.BASE_URL).build();
    }

    public List<Comment> getComments(boolean extractJson, boolean extractXml) throws IOException {

        WebClient.ResponseSpec response = webClient.get()
                .uri(ApiConstants.Resources.COMMENTS.resource)
                .retrieve();

        ApiConstants.checkForException(response.toBodilessEntity().block());

        Mono<List<Comment>> comments = response.bodyToMono(new ParameterizedTypeReference<List<Comment>>() {
        });

        if (extractJson) {
            ResourceExtractor.extractToJson(ApiConstants.Resources.COMMENTS.resource, comments);
        }

        if (extractXml) {
            ResourceExtractor.extractToXml(ApiConstants.Resources.COMMENTS.resource,
                    comments,
                    ApiConstants.Resources.COMMENTS.tagName);
        }

        return comments.block();
    }
    public Comment postComment(CommentsRequest request) throws UnexpectedException, ResourceFormatException {
            WebClient.ResponseSpec response  = webClient.post()
                    .uri(ApiConstants.Resources.COMMENTS.resource)
                    .body(Mono.just(createComment(request)), Comment.class)
                    .retrieve();

            ApiConstants.checkForException(response.toBodilessEntity().block());

            return response.bodyToMono(Comment.class).block();
    }

    public Comment putComment(CommentsRequest request) throws UnexpectedException, ResourceFormatException {

            String commentId ="/" + request.getId();

            WebClient.ResponseSpec response  = webClient.put()
                    .uri(ApiConstants.Resources.COMMENTS.resource + commentId)
                    .body(Mono.just(createComment(request)), Comment.class)
                    .retrieve();

            ApiConstants.checkForException(response.toBodilessEntity().block());

            return response.bodyToMono(Comment.class).block();
    }

    public Comment patchComment(CommentsRequest request) throws UnexpectedException, ResourceFormatException {
            String commentId ="/" + request.getId();

            WebClient.ResponseSpec response  = webClient.patch()
                    .uri(ApiConstants.Resources.COMMENTS.resource + commentId)
                    .body(Mono.just(createComment(request)), Comment.class)
                    .retrieve();

            ApiConstants.checkForException(response.toBodilessEntity().block());

            return response.bodyToMono(Comment.class).block();
    }

    public int deleteComment(int commentId) throws UnexpectedException {
            WebClient.ResponseSpec response = webClient.delete()
                    .uri(ApiConstants.Resources.COMMENTS.resource + "/" + commentId)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve();

            ApiConstants.checkForException(response.toBodilessEntity().block());


            return response.toBodilessEntity().block().getStatusCodeValue();

    }

        private Comment createComment(CommentsRequest request) throws ResourceFormatException {

            Comment newComment = new Comment();

            newComment.setId(request.getId());
            newComment.setPostId(request.getPostId());
            newComment.setEmail(request.getEmail());
            newComment.setName(request.getName());
            newComment.setBody(request.getBody());

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

            Set<ConstraintViolation<Comment>> constraintViolations = validator.validate(newComment);

            if (constraintViolations.size() > 0) {
                StringJoiner joiner = new StringJoiner(", ","Comment is not valid: ", "");
                Set<String> violationMessages = new HashSet<String>();

                for (ConstraintViolation<Comment> constraintViolation : constraintViolations) {
                    joiner.add(constraintViolation.getPropertyPath() +": "+constraintViolation.getMessage());
                }

                throw new ResourceFormatException(joiner.toString());
            }

            return newComment;
        }
}

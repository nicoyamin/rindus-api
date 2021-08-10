package com.example.rindus.service;

import com.example.rindus.ApiConstants;
import com.example.rindus.ResourceExtractor;
import com.example.rindus.entity.Album;
import com.example.rindus.exception.ResourceFormatException;
import com.example.rindus.model.AlbumRequest;
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
public class AlbumService {
    private final WebClient webClient;

    public AlbumService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(ApiConstants.BASE_URL).build();

    }

    public List<Album> getAlbums(boolean extractJson, boolean extractXml) throws IOException {

        WebClient.ResponseSpec response = webClient.get()
                .uri(ApiConstants.Resources.ALBUMS.resource)
                .retrieve();

        ApiConstants.checkForException(response.toBodilessEntity().block());

        Mono<List<Album>> albums = response.bodyToMono(new ParameterizedTypeReference<List<Album>>() {});

        if(extractJson) {
            ResourceExtractor.extractToJson(ApiConstants.Resources.ALBUMS.resource, albums);
        }

        if(extractXml) {
            ResourceExtractor.extractToXml(ApiConstants.Resources.ALBUMS.resource,
                    albums,
                    ApiConstants.Resources.ALBUMS.tagName);
        }

        return albums.block();
    }

    public Album postAlbum(AlbumRequest request) throws UnexpectedException, ResourceFormatException {
        WebClient.ResponseSpec response  = webClient.post()
                .uri(ApiConstants.Resources.ALBUMS.resource)
                .body(Mono.just(createAlbum(request)), Album.class)
                .retrieve();

        ApiConstants.checkForException(response.toBodilessEntity().block());

        return response.bodyToMono(Album.class).block();
    }

    public Album putAlbum(AlbumRequest request) throws UnexpectedException, ResourceFormatException {

        String albumId ="/" + request.getId();

        WebClient.ResponseSpec response  = webClient.put()
                .uri(ApiConstants.Resources.ALBUMS.resource + albumId)
                .body(Mono.just(createAlbum(request)), Album.class)
                .retrieve();

        ApiConstants.checkForException(response.toBodilessEntity().block());

        return response.bodyToMono(Album.class).block();
    }

    public Album patchAlbum(AlbumRequest request) throws UnexpectedException, ResourceFormatException {
        String albumId ="/" + request.getId();

        WebClient.ResponseSpec response  = webClient.patch()
                .uri(ApiConstants.Resources.ALBUMS.resource + albumId)
                .body(Mono.just(createAlbum(request)), Album.class)
                .retrieve();

        ApiConstants.checkForException(response.toBodilessEntity().block());

        return response.bodyToMono(Album.class).block();
    }

    public int deleteAlbum(int albumId) throws UnexpectedException {

        WebClient.ResponseSpec response = webClient.delete()
                .uri(ApiConstants.Resources.ALBUMS.resource + "/" + albumId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();

        ApiConstants.checkForException(response.toBodilessEntity().block());


        return response.toBodilessEntity().block().getStatusCodeValue();

    }

    private Album createAlbum(AlbumRequest request) throws ResourceFormatException {

        Album newAlbum = new Album();

        newAlbum.setId(request.getId());
        newAlbum.setUserId(request.getUserId());
        newAlbum.setTitle(request.getTitle());

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<Album>> constraintViolations = validator.validate(newAlbum);

        if (constraintViolations.size() > 0) {
            StringJoiner joiner = new StringJoiner(", ","Album is not valid: ", "");
            Set<String> violationMessages = new HashSet<String>();

            for (ConstraintViolation<Album> constraintViolation : constraintViolations) {
                joiner.add(constraintViolation.getPropertyPath() +": "+constraintViolation.getMessage());
            }

            throw new ResourceFormatException(joiner.toString());
        }

        return newAlbum;
    }
}

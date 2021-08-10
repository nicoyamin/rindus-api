package com.example.rindus.service;

import com.example.rindus.ApiConstants;
import com.example.rindus.ResourceExtractor;
import com.example.rindus.entity.Photo;
import com.example.rindus.exception.ResourceFormatException;
import com.example.rindus.model.PhotoRequest;
import io.swagger.annotations.Api;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.rmi.UnexpectedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

@Service
public class PhotoService {
    private final WebClient webClient;

    public PhotoService(WebClient.Builder webClientBuilder) {
        this.webClient = WebClient.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
                .baseUrl(ApiConstants.BASE_URL).build();
    }

    public List<Photo> getPhotos(boolean extractJson, boolean extractXml) throws IOException {

        WebClient.ResponseSpec response = webClient.get()
                .uri(ApiConstants.Resources.PHOTOS.resource)
                .retrieve();

        checkForException(response.toBodilessEntity().block());

        Mono<List<Photo>> photos = response.bodyToMono(new ParameterizedTypeReference<List<Photo>>() {});

        if(extractJson) {
            ResourceExtractor.extractToJson(ApiConstants.Resources.PHOTOS.resource, photos);
        }

        if(extractXml) {
            ResourceExtractor.extractToXml(ApiConstants.Resources.PHOTOS.resource,
                    photos,
                    ApiConstants.Resources.PHOTOS.tagName);
        }

        return photos.block();
    }

    public Photo postPhoto(PhotoRequest request) throws ResourceFormatException, UnexpectedException {
        WebClient.ResponseSpec response  = webClient.post()
                .uri(ApiConstants.Resources.PHOTOS.resource)
                .body(Mono.just(createPhoto(request)), Photo.class)
                .retrieve();

        checkForException(response.toBodilessEntity().block());

        return response.bodyToMono(Photo.class).block();

    }

    public Photo putPhoto(PhotoRequest request) throws ResourceFormatException, UnexpectedException {

        String photoId ="/" + request.getId();

        WebClient.ResponseSpec response  = webClient.put()
                .uri(ApiConstants.Resources.PHOTOS.resource + photoId)
                .body(Mono.just(createPhoto(request)), Photo.class)
                .retrieve();

        checkForException(response.toBodilessEntity().block());

        return response.bodyToMono(Photo.class).block();
    }

    public Photo patchPhoto(PhotoRequest request) throws ResourceFormatException, UnexpectedException {

        String photoId ="/" + request.getId();

        WebClient.ResponseSpec response  = webClient.patch()
                .uri(ApiConstants.Resources.PHOTOS.resource + photoId)
                .body(Mono.just(createPhoto(request)), Photo.class)
                .retrieve();

        checkForException(response.toBodilessEntity().block());

        return response.bodyToMono(Photo.class).block();
    }

    public int deletePhoto(int photoId) throws UnexpectedException {

        WebClient.ResponseSpec response = webClient.delete()
                .uri(ApiConstants.Resources.PHOTOS.resource + "/" + photoId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();

        checkForException(response.toBodilessEntity().block());


        return response.toBodilessEntity().block().getStatusCodeValue();

    }

    private Photo createPhoto(PhotoRequest request) throws ResourceFormatException {

        Photo newPhoto = new Photo();

        newPhoto.setId(request.getId());
        newPhoto.setAlbumId(request.getAlbumId());
        newPhoto.setTitle(request.getTitle());
        newPhoto.setUrl(request.getUrl());
        newPhoto.setThumbnailUrl(request.getThumbnailUrl());

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<Photo>> constraintViolations = validator.validate(newPhoto);

        if (constraintViolations.size() > 0) {
            StringJoiner joiner = new StringJoiner(", ","Photo is not valid: ", "");
            Set<String> violationMessages = new HashSet<String>();

            for (ConstraintViolation<Photo> constraintViolation : constraintViolations) {
                joiner.add(constraintViolation.getPropertyPath() +": "+constraintViolation.getMessage());
            }

            throw new ResourceFormatException(joiner.toString());
        }

        return newPhoto;
    }

    private void checkForException(ResponseEntity status) throws UnexpectedException {
        if(!ApiConstants.ACCEPTED_CODES.contains(status.getStatusCodeValue())) {
            throw new UnexpectedException(status.getBody().toString());
        }
    }
}

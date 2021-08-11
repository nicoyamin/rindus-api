package com.example.rindus.service;

import com.example.rindus.ApiConstants;
import com.example.rindus.TestUtils;
import com.example.rindus.entity.Photo;
import com.example.rindus.exception.ResourceFormatException;
import com.example.rindus.model.PhotoRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
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
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PhotoServiceTest {

    @Mock
    WebClient webClient;

    @Mock
    PhotoRequest request;

    @Mock
    WebClient.ResponseSpec response;

    @Mock
    Mono<List<Photo>> photos;

    @Mock
    Mono<Photo> photoMono;

    @Mock
    List<Photo> photoList;

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
    Photo photo;

    PhotoService service;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(webClientBuilder.codecs(any(Consumer.class))).thenReturn(webClientBuilder);
        when(webClientBuilder.baseUrl(any(String.class))).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);
        service = new PhotoService(webClientBuilder);
    }

    @Test
    public void shouldGetPhotosAndReturnPhotoList() throws IOException {

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/photos")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(any(ParameterizedTypeReference.class))).thenReturn(photos);
        when(photos.block()).thenReturn(photoList);

        List<Photo> responseList = service.getPhotos(false, false);

        assertEquals(responseList, photoList);
    }

    @Test
    public void shouldPostPhotoAndReturnCreatedPhoto() throws UnexpectedException, ResourceFormatException {
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/photos")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(Photo.class)).thenReturn(photoMono);
        when(photoMono.block()).thenReturn(photo);

        Photo response = service.postPhoto(TestUtils.createDummyPhotoRequest());

        assertEquals(response, photo);
    }

    @Test
    public void shouldUpdatePhotoAndReturnUpdatedPhoto() throws UnexpectedException, ResourceFormatException {
        when(webClient.put()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/photos/1")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(Photo.class)).thenReturn(photoMono);
        when(photoMono.block()).thenReturn(photo);

        Photo response = service.putPhoto(TestUtils.createDummyPhotoRequest());

        assertEquals(response, photo);
    }

    @Test
    public void shouldPatchPhotoAndReturnPatchedPhoto() throws UnexpectedException, ResourceFormatException {
        when(webClient.patch()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/photos/1")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(Photo.class)).thenReturn(photoMono);
        when(photoMono.block()).thenReturn(photo);

        Photo response = service.patchPhoto(TestUtils.createDummyPhotoRequest());

        assertEquals(response, photo);
    }

    @Test
    public void shouldDeletePhotoAndReturnDeletedPhotoId() throws UnexpectedException, ResourceFormatException {
        when(webClient.delete()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/photos/1")).thenReturn(requestBodySpec);
        when(requestBodySpec.accept(MediaType.APPLICATION_JSON)).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(Photo.class)).thenReturn(photoMono);
        when(photoMono.block()).thenReturn(photo);

        int response = service.deletePhoto(1);

        assertEquals(response, HttpStatus.OK.value());
    }

    @Test
    public void shouldTakeAnInvalidPhotoRequestAndThrowResourceFormatException() {
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/photos")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(Photo.class)).thenReturn(photoMono);
        when(photoMono.block()).thenReturn(photo);

        ResourceFormatException exception = assertThrows(ResourceFormatException.class, () -> {
            service.postPhoto(TestUtils.createInvalidPhotoRequest());
        });

        String expectedMessage = "Photo is not valid: url: Invalid URL format";

        assertEquals(expectedMessage, exception.getMessage());

    }
}
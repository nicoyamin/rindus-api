package com.example.rindus.service;

import com.example.rindus.ApiConstants;
import com.example.rindus.ResourceExtractor;
import com.example.rindus.TestUtils;
import com.example.rindus.entity.Album;
import com.example.rindus.exception.ResourceFormatException;
import com.example.rindus.model.AlbumRequest;
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
import java.lang.reflect.ParameterizedType;
import java.rmi.UnexpectedException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AlbumServiceTest {

    @Mock
    WebClient webClient;

    @Mock
    AlbumRequest request;

    @Mock
    WebClient.ResponseSpec response;

    @Mock
    Mono<List<Album>> albums;

    @Mock
    Mono<Album> albumMono;

    @Mock
    List<Album> albumList;

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
    Album album;

    AlbumService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(webClientBuilder.baseUrl(any(String.class))).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);
        service = new AlbumService(webClientBuilder);
    }

    @Test
    public void shouldGetAlbumsAndReturnAlbumList() throws IOException {

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/albums")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);
        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(any(ParameterizedTypeReference.class))).thenReturn(albums);
        when(albums.block()).thenReturn(albumList);

        List<Album> response = service.getAlbums(false, false);

        assertEquals(response, albumList);
    }

    @Test
    public void shouldPostAlbumAndReturnCreatedAlbum() throws UnexpectedException, ResourceFormatException {
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/albums")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(Album.class)).thenReturn(albumMono);
        when(albumMono.block()).thenReturn(album);

        Album response = service.postAlbum(TestUtils.createDummyAlbumRequest());

        assertEquals(response, album);
    }

    @Test
    public void shouldUpdateAlbumAndReturnUpdatedAlbum() throws UnexpectedException, ResourceFormatException {
        when(webClient.put()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/albums/1")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(Album.class)).thenReturn(albumMono);
        when(albumMono.block()).thenReturn(album);

        Album response = service.putAlbum(TestUtils.createDummyAlbumRequest());

        assertEquals(response, album);
    }

    @Test
    public void shouldPatchAlbumAndReturnPatchedAlbum() throws UnexpectedException, ResourceFormatException {
        when(webClient.patch()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/albums/1")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(Album.class)).thenReturn(albumMono);
        when(albumMono.block()).thenReturn(album);

        Album response = service.patchAlbum(TestUtils.createDummyAlbumRequest());

        assertEquals(response, album);
    }

    @Test
    public void shouldDeleteAlbumAndReturnDeletedAlbumId() throws UnexpectedException, ResourceFormatException {
        when(webClient.delete()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/albums/1")).thenReturn(requestBodySpec);
        when(requestBodySpec.accept(MediaType.APPLICATION_JSON)).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(Album.class)).thenReturn(albumMono);
        when(albumMono.block()).thenReturn(album);

        int response = service.deleteAlbum(1);

        assertEquals(response, HttpStatus.OK.value());
    }

    @Test
    public void shouldTakeAnInvalidAlbumRequestAndThrowResourceFormatException() {
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("/albums")).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);

        when(response.toBodilessEntity()).thenReturn(monoResponseEntity);
        when(monoResponseEntity.block()).thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        when(response.bodyToMono(Album.class)).thenReturn(albumMono);
        when(albumMono.block()).thenReturn(album);

        ResourceFormatException exception = assertThrows(ResourceFormatException.class, () -> {
            service.postAlbum(TestUtils.createInvalidAlbumRequest());
        });

        String expectedMessage = "Album is not valid: id: This field must be positive";

        assertEquals(expectedMessage, exception.getMessage());

    }
}
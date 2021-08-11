package com.example.rindus.controller;

import com.example.rindus.TestConstants;
import com.example.rindus.TestUtils;
import com.example.rindus.entity.Album;
import com.example.rindus.exception.ResourceFormatException;
import com.example.rindus.model.AlbumRequest;
import com.example.rindus.service.AlbumService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AlbumControllerTest {

    @Mock
    AlbumService service;

    AlbumController controller;

    AlbumRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new AlbumRequest();
        controller= new AlbumController(service);
    }

    @Test
    public void shouldGetAlbumsAndReturnAlbumListAnd200Response() throws IOException {


        when(service.getAlbums(any(Boolean.class), any(Boolean.class))).thenReturn(TestUtils.createDummyAlbumList());

        ResponseEntity response = controller.getAlbums(false, false);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), TestUtils.createDummyAlbumList());
    }

    @Test
    public void shouldCreateAlbumAndReturnCreatedAlbumAnd200Response() throws IOException, ResourceFormatException {


        when(service.postAlbum(request)).thenReturn(TestUtils.createDummyAlbum());

        ResponseEntity response = controller.postAlbum(request);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), TestUtils.createDummyAlbum());
    }

    @Test
    public void shouldUpdateAlbumAndReturnUpdatedAlbumAnd200Response() throws IOException, ResourceFormatException {


        when(service.putAlbum(request)).thenReturn(TestUtils.createDummyAlbum());

        ResponseEntity response = controller.putAlbum(request);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), TestUtils.createDummyAlbum());
    }

    @Test
    public void shouldPatchAlbumAndReturnPatchedAlbumAnd200Response() throws IOException, ResourceFormatException {


        when(service.patchAlbum(request)).thenReturn(TestUtils.createDummyAlbum());

        ResponseEntity response = controller.patchAlbum(request);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), TestUtils.createDummyAlbum());
    }

    @Test
    public void shouldDeleteAlbumAndReturnDeletedAlbumIdAnd200Response() throws IOException, ResourceFormatException {


        when(service.deleteAlbum(1)).thenReturn(1);

        ResponseEntity response = controller.deleteAlbum(1);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), 1);
    }
}

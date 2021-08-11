package com.example.rindus.controller;

import com.example.rindus.entity.Photo;
import com.example.rindus.exception.ResourceFormatException;
import com.example.rindus.model.PhotoRequest;
import com.example.rindus.service.PhotoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PhotoControllerTest {
    @Mock
    PhotoService service;

    @Mock
    PhotoRequest request;

    @Mock
    Photo photo;

    PhotoController controller;

    @Mock
    List<Photo> photos;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller= new PhotoController(service);
    }

    @Test
    public void shouldGetPhotosAndReturnPhotoListAnd200Response() throws IOException {


        when(service.getPhotos(any(Boolean.class), any(Boolean.class))).thenReturn(photos);

        ResponseEntity response = controller.getPhotos(false, false);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), photos);
    }

    @Test
    public void shouldCreatePhotoAndReturnCreatedPhotoAnd200Response() throws IOException, ResourceFormatException {


        when(service.postPhoto(request)).thenReturn(photo);

        ResponseEntity response = controller.postPhoto(request);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), photo);
    }

    @Test
    public void shouldUpdatePhotoAndReturnUpdatedPhotoAnd200Response() throws IOException, ResourceFormatException {


        when(service.putPhoto(request)).thenReturn(photo);

        ResponseEntity response = controller.putPhoto(request);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), photo);
    }

    @Test
    public void shouldPatchPhotoAndReturnPatchedPhotoAnd200Response() throws IOException, ResourceFormatException {


        when(service.patchPhoto(request)).thenReturn(photo);

        ResponseEntity response = controller.patchPhoto(request);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), photo);
    }

    @Test
    public void shouldDeletePhotoAndReturnDeletedPhotoIdAnd200Response() throws IOException, ResourceFormatException {


        when(service.deletePhoto(1)).thenReturn(1);

        ResponseEntity response = controller.deletePhoto(1);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), 1);
    }
}

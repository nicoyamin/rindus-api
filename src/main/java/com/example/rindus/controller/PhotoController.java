package com.example.rindus.controller;

import com.example.rindus.entity.Photo;
import com.example.rindus.entity.Photo;
import com.example.rindus.model.PhotoRequest;
import com.example.rindus.service.PhotoService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class PhotoController implements PhotoApi{

    private PhotoService photoService;


    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @SneakyThrows
    @Override
    public ResponseEntity<List<Photo>> getPhotos(boolean extractJson, boolean extractXml) {
        List<Photo> photos = photoService.getPhotos(extractJson, extractXml);
        return ResponseEntity.ok(photos);
    }

    @Override
    public ResponseEntity<Photo> postPhoto(@Valid PhotoRequest request) {
        Photo createdPhoto = photoService.postPhoto(request);
        return ResponseEntity.ok(createdPhoto);
    }

    @Override
    public ResponseEntity<Photo> putPhoto(@Valid PhotoRequest request) {
        Photo updatedPhoto = photoService.putPhoto(request);
        return ResponseEntity.ok(updatedPhoto);
    }

    @Override
    public ResponseEntity<Photo> patchPhoto(@Valid PhotoRequest request) {
        Photo updatedPhoto = photoService.patchPhoto(request);
        return ResponseEntity.ok(updatedPhoto);
    }

    @Override
    public ResponseEntity deletePhoto(@Valid int photoId) {
        photoService.deletePhoto(photoId);
        return ResponseEntity.ok("Photo with Id " + photoId + " deleted successfully");
    }
}

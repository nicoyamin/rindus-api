package com.example.rindus.controller;

import com.example.rindus.entity.Album;
import com.example.rindus.entity.Post;
import com.example.rindus.model.AlbumRequest;
import com.example.rindus.service.AlbumService;
import com.example.rindus.service.PostService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AlbumController implements AlbumApi {

    private AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @SneakyThrows
    @Override
    public ResponseEntity<List<Album>> getAlbums(boolean extractJson, boolean extractXml) {
        List<Album> albums = albumService.getAlbums(extractJson, extractXml);
        return ResponseEntity.ok(albums);
    }

    @Override
    public ResponseEntity<Album> postAlbum(@Valid AlbumRequest request) {
        Album createdAlbum = albumService.postAlbum(request);
        return ResponseEntity.ok(createdAlbum);
    }

    @Override
    public ResponseEntity<Album> putAlbum(@Valid AlbumRequest request) {
        Album updatedAlbum = albumService.putAlbum(request);
        return ResponseEntity.ok(updatedAlbum);
    }

    @Override
    public ResponseEntity<Album> patchAlbum(@Valid AlbumRequest request) {
        Album updatedAlbum = albumService.patchAlbum(request);
        return ResponseEntity.ok(updatedAlbum);
    }

    @Override
    public ResponseEntity deleteAlbum(@Valid int albumId) {
        albumService.deleteAlbum(albumId);
        return ResponseEntity.ok("Post with Id " + albumId + " deleted successfully");    }
}

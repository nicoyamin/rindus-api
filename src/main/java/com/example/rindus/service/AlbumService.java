package com.example.rindus.service;

import com.example.rindus.ApiConstants;
import com.example.rindus.ResourceExtractor;
import com.example.rindus.entity.Album;
import com.example.rindus.entity.Post;
import com.example.rindus.model.AlbumRequest;
import com.example.rindus.model.PostRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@Service
public class AlbumService {
    private final WebClient webClient;

    public AlbumService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(ApiConstants.BASE_URL).build();

    }

    public List<Album> getAlbums(boolean extractJson, boolean extractXml) throws IOException {

        Mono<List<Album>> response = webClient.get()
                .uri(ApiConstants.Resources.ALBUMS.resource)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Album>>() {});

        List<Album> albums = response.block();

        if(extractJson) {
            ResourceExtractor.extractToJson(ApiConstants.Resources.ALBUMS.resource, response);
        }

        if(extractXml) {
            ResourceExtractor.extractToXml(ApiConstants.Resources.ALBUMS.resource,
                    response,
                    ApiConstants.Resources.ALBUMS.tagName);
        }

        return albums;
    }

    public Album postAlbum(AlbumRequest request) {
        Mono<Album> response = webClient.post()
                .uri(ApiConstants.Resources.ALBUMS.resource)
                .body(Mono.just(request), AlbumRequest.class)
                .retrieve()
                .bodyToMono(Album.class);

        return  response.block();
    }

    public Album putAlbum(AlbumRequest request) {

        String albumId ="/" + request.getId();

        Mono<Album> response = webClient.put()
                .uri(ApiConstants.Resources.ALBUMS.resource + albumId)
                .body(Mono.just(request), AlbumRequest.class)
                .retrieve()
                .bodyToMono(Album.class);

        return  response.block();
    }

    public Album patchAlbum(AlbumRequest request) {

        String albumId ="/" + request.getId();

        Mono<Album> response = webClient.patch()
                .uri(ApiConstants.Resources.ALBUMS.resource + albumId)
                .body(Mono.just(request), AlbumRequest.class)
                .retrieve()
                .bodyToMono(Album.class);

        return  response.block();
    }

    public int deleteAlbum(int albumId) {

        WebClient.ResponseSpec response = webClient.delete()
                .uri(ApiConstants.Resources.ALBUMS.resource + "/" + albumId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();


        return response.toBodilessEntity().block().getStatusCodeValue();

    }
}

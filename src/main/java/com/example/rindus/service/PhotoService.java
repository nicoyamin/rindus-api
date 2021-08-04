package com.example.rindus.service;

import com.example.rindus.ApiConstants;
import com.example.rindus.ResourceExtractor;
import com.example.rindus.entity.Photo;
import com.example.rindus.model.PhotoRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@Service
public class PhotoService {
    private final WebClient webClient;

    public PhotoService(WebClient.Builder webClientBuilder) {
        this.webClient = WebClient.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
                .baseUrl(ApiConstants.BASE_URL).build();
    }

    public List<Photo> getPhotos(boolean extractJson, boolean extractXml) throws IOException {

        Mono<List<Photo>> response = webClient.get()
                .uri(ApiConstants.Resources.PHOTOS.resource)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Photo>>() {});

        List<Photo> photos = response.block();

        if(extractJson) {
            ResourceExtractor.extractToJson(ApiConstants.Resources.PHOTOS.resource, response);
        }

        if(extractXml) {
            ResourceExtractor.extractToXml(ApiConstants.Resources.PHOTOS.resource,
                    response,
                    ApiConstants.Resources.PHOTOS.tagName);
        }

        return photos;
    }

    public Photo postPhoto(PhotoRequest request) {
        Mono<Photo> response = webClient.post()
                .uri(ApiConstants.Resources.PHOTOS.resource)
                .body(Mono.just(request), PhotoRequest.class)
                .retrieve()
                .bodyToMono(Photo.class);

        return  response.block();
    }

    public Photo putPhoto(PhotoRequest request) {

        String photoId ="/" + request.getId();

        Mono<Photo> response = webClient.put()
                .uri(ApiConstants.Resources.PHOTOS.resource + photoId)
                .body(Mono.just(request), PhotoRequest.class)
                .retrieve()
                .bodyToMono(Photo.class);

        return  response.block();
    }

    public Photo patchPhoto(PhotoRequest request) {

        String photoId ="/" + request.getId();

        Mono<Photo> response = webClient.patch()
                .uri(ApiConstants.Resources.PHOTOS.resource + photoId)
                .body(Mono.just(request), PhotoRequest.class)
                .retrieve()
                .bodyToMono(Photo.class);

        return  response.block();
    }

    public int deletePhoto(int photoId) {

        WebClient.ResponseSpec response = webClient.delete()
                .uri(ApiConstants.Resources.PHOTOS.resource + "/" + photoId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();


        return response.toBodilessEntity().block().getStatusCodeValue();

    }
}

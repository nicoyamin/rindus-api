package com.example.rindus;

import io.swagger.models.auth.In;
import org.springframework.http.HttpStatus;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ApiConstants {

    public static final String BASE_URL = "http://jsonplaceholder.typicode.com";
    public static final String EXTRACTED_PATH = "src\\main\\resources\\extracted-resources";
    public static final Set<Integer> ACCEPTED_CODES =  Stream.of(HttpStatus.OK.value(),
            HttpStatus.ACCEPTED.value(),
            HttpStatus.CREATED.value(),
            HttpStatus.NO_CONTENT.value())
            .collect(Collectors.toCollection(HashSet::new));


    public enum Resources {
        POSTS("/posts", "Post"),
        COMMENTS("/comments", "Comment"),
        ALBUMS("/albums", "Album"),
        PHOTOS("/photos", "Photo"),
        USERS("/users", "User"),
        TODOS("/todos", "Todo");

        public final String resource;
        public final String tagName;

        Resources(String resource, String tagName) {
            this.resource = resource;
            this.tagName = tagName;
        }

    }


    private ApiConstants() {

    }
}

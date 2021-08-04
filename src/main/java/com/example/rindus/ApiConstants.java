package com.example.rindus;

public final class ApiConstants {

    public static final String BASE_URL = "http://jsonplaceholder.typicode.com";
    public static final String EXTRACTED_PATH = "src\\main\\resources\\extracted-resources";

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

package com.example.rindus;

public final class ApiConstants {

    public static final String BASE_URL = "http://jsonplaceholder.typicode.com";

    public enum Resources {
        POSTS("/posts"),
        COMMENTS("/comments"),
        ALBUMS("/albums"),
        PHOTOS("/photos"),
        USERS("/users"),
        TODOS("/todos");

        public final String resource;

        Resources(String s) {
            this.resource = s;
        }

    }

    private ApiConstants() {

    }
}

package com.example.rindus.model;

import lombok.Data;

@Data
public class PostRequest {

    Integer id;
    Integer userId;
    String title;
    String body;
}

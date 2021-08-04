package com.example.rindus.entity;

import lombok.Data;

@Data
public class Post {
    Integer id;
    Integer userId;
    String title;
    String body;
}

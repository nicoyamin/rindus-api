package com.example.rindus.entity;

import lombok.Data;

@Data
public class Comment {
    Integer id;
    Integer postId;
    String email;
    String name;
    String body;
}

package com.example.rindus.model;

import lombok.Data;

@Data
public class CommentsRequest {
    Integer id;
    Integer postId;
    String email;
    String name;
    String body;
}

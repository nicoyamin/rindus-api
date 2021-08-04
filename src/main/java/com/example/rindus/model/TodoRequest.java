package com.example.rindus.model;

import lombok.Data;

@Data
public class TodoRequest {

    Integer id;
    Integer userId;
    String title;
    Boolean completed;
}

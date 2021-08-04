package com.example.rindus.entity;

import lombok.Data;

@Data
public class Todo {
    Integer id;
    Integer userId;
    String title;
    Boolean completed;

}

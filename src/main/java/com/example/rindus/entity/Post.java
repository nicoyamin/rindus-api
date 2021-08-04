package com.example.rindus.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Post {

    @Id
    Integer id;

    Integer userId;

    String title;

    String body;
}

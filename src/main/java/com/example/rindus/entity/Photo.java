package com.example.rindus.entity;

import lombok.Data;

@Data
public class Photo {

    Integer id;
    Integer albumId;
    String title;
    String url;
    String thumbnailUrl;

}

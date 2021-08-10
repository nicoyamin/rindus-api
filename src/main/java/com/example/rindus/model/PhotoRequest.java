package com.example.rindus.model;

import lombok.Data;

@Data
public class PhotoRequest {


    Integer id;
    Integer albumId;
    String title;
    String url;
    String thumbnailUrl;
}

package com.example.rindus.model;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class AlbumRequest {

    Integer id;
    Integer userId;
    String title;

}

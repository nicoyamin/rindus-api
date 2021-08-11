package com.example.rindus.model;

import com.example.rindus.ValidationMessages;
import io.swagger.models.auth.In;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class AlbumRequest {

    Integer id;
    Integer userId;
    String title;

}

package com.example.rindus.entity;

import com.example.rindus.ValidationMessages;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class Photo {

    @NotNull(message = ValidationMessages.NOT_NULL)
    @Positive(message = ValidationMessages.POSITIVE)
    Integer id;

    @NotNull(message = ValidationMessages.NOT_NULL)
    @Positive(message = ValidationMessages.POSITIVE)
    Integer albumId;

    String title;

    @URL(message = ValidationMessages.URL)
    String url;

    @URL(message = ValidationMessages.URL)
    String thumbnailUrl;

}

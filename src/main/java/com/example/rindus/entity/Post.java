package com.example.rindus.entity;

import com.example.rindus.ValidationMessages;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class Post {
    @NotNull(message = ValidationMessages.NOT_NULL)
    @Positive(message = ValidationMessages.POSITIVE)
    Integer id;

    @NotNull(message = ValidationMessages.NOT_NULL)
    @Positive(message = ValidationMessages.POSITIVE)
    Integer userId;

    String title;

    String body;
}

package com.example.rindus.entity;

import com.example.rindus.ValidationMessages;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class Comment {
    @NotNull(message = ValidationMessages.NOT_NULL)
    @Positive(message = ValidationMessages.POSITIVE)
    Integer id;

    @NotNull(message = ValidationMessages.NOT_NULL)
    @Positive(message = ValidationMessages.POSITIVE)
    Integer postId;

    @Email(message = ValidationMessages.EMAIL)
    String email;

    String name;

    String body;
}

package com.example.rindus.entity;

import com.example.rindus.ValidationMessages;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class Todo {

    @NotNull(message = ValidationMessages.NOT_NULL)
    @Positive(message = ValidationMessages.POSITIVE)
    Integer id;

    @NotNull(message = ValidationMessages.NOT_NULL)
    @Positive(message = ValidationMessages.POSITIVE)
    Integer userId;

    String title;

    @NotNull(message = ValidationMessages.NOT_NULL)
    Boolean completed;

}

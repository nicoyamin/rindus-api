package com.example.rindus.entity;

import com.example.rindus.ValidationMessages;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class User {

    @NotNull(message = ValidationMessages.NOT_NULL)
    @Positive(message = ValidationMessages.POSITIVE)
    Integer id;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String name;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String username;

    @Email(message = ValidationMessages.EMAIL)
    String email;

    Address address;

    //No validation for phone since API accepts lots of inputs
    String phone;

    @Pattern(regexp = "^[a-z0-9]+\\.[a-z]", message = ValidationMessages.WEBSITE)
    String website;

    Company company;

}

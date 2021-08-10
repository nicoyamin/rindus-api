package com.example.rindus.entity;

import com.example.rindus.ValidationMessages;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class Coordinates {

    @Pattern(regexp = "-{0,1}[0-9]+[.][0-9]+", message = ValidationMessages.COORDINATES)
    String lat;

    @Pattern(regexp = "-{0,1}[0-9]+[.][0-9]+", message = ValidationMessages.COORDINATES)
    String lng;
}

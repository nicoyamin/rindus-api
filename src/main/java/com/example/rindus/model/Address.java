package com.example.rindus.model;

import lombok.Data;

@Data
public class Address {

    String street;
    String suite;
    String city;
    String zipCode;
    Coordinates geo;
}

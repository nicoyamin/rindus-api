package com.example.rindus.entity;

import lombok.Data;

@Data
public class Address {

    String street;
    String suite;
    String city;
    String zipcode;
    Coordinates geo;
}

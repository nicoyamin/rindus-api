package com.example.rindus.model;

import lombok.Data;

@Data
public class User {

    Integer id;
    String name;
    String username;
    String email;
    Address address;
    String phone;
    String website;
    Company company;

}

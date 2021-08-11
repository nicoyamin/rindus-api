package com.example.rindus.model;

import com.example.rindus.entity.Address;
import com.example.rindus.entity.Company;
import lombok.Data;

@Data
public class UserRequest {


    Integer id;

    String name;

    String username;

    String email;

    Address address;

    String phone;

    String website;

    Company company;
}

package com.example.parqueaderoApi.model;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String email;
    private String password;
}
package com.example.parqueaderoApi.security.config;

import lombok.Data;

@Data
public class AuthCredentials {
    private String email;
    private String password;
}

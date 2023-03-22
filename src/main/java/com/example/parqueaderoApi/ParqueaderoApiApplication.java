package com.example.parqueaderoApi;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@SecurityScheme(
		name = "bearerAuth",
		description = "JWT Authorization ejemplo: \"Authorization: Bearer {token}\"",
		type = SecuritySchemeType.HTTP,
		scheme = "bearer",
		bearerFormat = "JWT",
		in = SecuritySchemeIn.HEADER)
public class ParqueaderoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParqueaderoApiApplication.class, args);
	}


}

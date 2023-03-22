package com.example.parqueaderoApi.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;


import java.security.Key;
import java.util.*;

public class TokenUtils {
    private final static String ACCESS_TOKEN_SECRET = "fdfs4f5s54f545sdffdZZzUlp0wslPod";
    private final static Long ACCES_TOKEN_VALIDITY_SECONDS = 2_592_000L;

    public static String createToken(String nombre, String email){
        long expirationTime = ACCES_TOKEN_VALIDITY_SECONDS * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis()+ expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre", nombre);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact()
                ;
    }
    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        }catch (JwtException e){
            return null;
        }
    }


}

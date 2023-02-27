package com.example.parqueaderoApi.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  
  @Autowired
  private AuthenticationEntryPoint basicSecurityEntryPoint;
  
  @Autowired
  private AuthenticationProvider authProvider;
  
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .requestMatchers("/api/registrar", "/parqueadero/obtenerParqueaderos")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic();
    return http.build();
  }
  
  @Autowired
  public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authProvider);
  }
  
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}

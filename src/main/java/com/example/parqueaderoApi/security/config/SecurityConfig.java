package com.example.parqueaderoApi.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfig {


  private final UserDetailsService userDetailsService;
  private final JWTAuthorizationFilter jwtAuthorizationFilter;
  @Autowired
  private AuthenticationProvider authProvider;

  public SecurityConfig(  @Qualifier("UserDetailServiceImpl") UserDetailsService userDetailsService, JWTAuthorizationFilter jwtAuthorizationFilter) {
    this.userDetailsService = userDetailsService;
    this.jwtAuthorizationFilter = jwtAuthorizationFilter;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
    JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
    jwtAuthenticationFilter.setAuthenticationManager(authManager);
    jwtAuthenticationFilter.setFilterProcessesUrl("/login");
    http.csrf()
            .disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
        
            .authorizeHttpRequests(
                authorizationManagerRequestMatcherRegistry ->
                    authorizationManagerRequestMatcherRegistry
                        .requestMatchers(HttpMethod.POST, "/register/**")
                        .permitAll()
                        .requestMatchers("/swagger-ui/**","/v3/api-docs/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
            )
        
            .addFilter(jwtAuthenticationFilter)
            .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
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

//  @Bean
  //UserDetailsService userDetailsService(){
    //InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//    manager.createUser(User.withUsername("admin")
//            .password(passwordEncoder().encode("admin"))
 //           .roles()
  //          .build());
   // return manager;
  //}

  @Bean
  AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder())
            .and()
            .build();
  }

}

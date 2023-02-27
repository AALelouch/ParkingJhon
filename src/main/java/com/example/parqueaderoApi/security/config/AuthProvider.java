package com.example.parqueaderoApi.security.config;

import com.example.parqueaderoApi.entity.User;
import com.example.parqueaderoApi.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class AuthProvider implements AuthenticationProvider {
  
  @Autowired
  private UserRepository userRepository;
  
  @Override
  public Authentication authenticate(final Authentication authentication)
      throws AuthenticationException {
    
    String email = authentication.getName();
    String password = authentication.getCredentials().toString();
    System.out.println(email);
    System.out.println(password);
  
    User user = userRepository.findByEmail(email).orElseThrow(
        () -> new IllegalArgumentException("User not found"));
    
    if (!user.getPassword().equals(password)) throw new IllegalArgumentException("Password incorrect");
  
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority("admin"));
  
    return new UsernamePasswordAuthenticationToken(email, password, authorities);
  }
  
  @Override
  public boolean supports(final Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}

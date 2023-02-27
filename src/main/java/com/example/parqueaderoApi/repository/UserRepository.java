package com.example.parqueaderoApi.repository;

import com.example.parqueaderoApi.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  
  Optional<User> findByEmail(String email);
  
}

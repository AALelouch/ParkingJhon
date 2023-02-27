package com.example.parqueaderoApi.repository;

import com.example.parqueaderoApi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

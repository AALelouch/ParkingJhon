package com.example.parqueaderoApi.repository;

import com.example.parqueaderoApi.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface CarroRepositorio extends JpaRepository<Car, String> {
    Optional<Car> exitCarro(String id);
}

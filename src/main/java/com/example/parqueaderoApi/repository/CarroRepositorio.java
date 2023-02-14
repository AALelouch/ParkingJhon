package com.example.parqueaderoApi.repository;

import com.example.parqueaderoApi.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface CarroRepositorio extends JpaRepository<Carro, String> {

    @Query ("Select carro from Carro carro where carro.fechaDeSalida = null order by carro.placa limit 1")
    Optional<Carro> exitCarro();
}

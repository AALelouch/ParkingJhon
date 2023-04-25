package com.example.parqueaderoApi.repository;

import com.example.parqueaderoApi.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ParkingRepositorio extends JpaRepository<Parking, Long>{
    @Query("Select parking from Parking parking where parking.estado = 1 order by parking.id")
    List<Parking> getAllParkingAvailable();

    @Query("Select parking from Parking parking where parking.estado = 1 order by parking.id limit 1")
    Optional<Parking> getFirstParkingAvailable();

    @Query("select parking from Parking parking where parking.estado = 0 order by parking.id limit 1")
    Optional<Parking> exitParking();

}

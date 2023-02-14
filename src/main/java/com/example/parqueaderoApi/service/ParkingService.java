package com.example.parqueaderoApi.service;

import com.example.parqueaderoApi.entity.Carro;
import com.example.parqueaderoApi.entity.Parking;

import java.util.List;

public interface ParkingService {
    List<Parking>getParkingPorDisponibilidad(Boolean estado);
    void createParking(Parking parking);
    void exitParking();

}

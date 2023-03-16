package com.example.parqueaderoApi.service;

import com.example.parqueaderoApi.entity.Carro;
import com.example.parqueaderoApi.entity.Parking;

import com.example.parqueaderoApi.model.ParkingRequest;
import com.example.parqueaderoApi.model.ParkingResponse;
import java.util.List;

public interface ParkingService {
    List<ParkingResponse>getParkingPorDisponibilidad(Boolean estado);
    void createParking(ParkingRequest parking);
    void exitParking();

}

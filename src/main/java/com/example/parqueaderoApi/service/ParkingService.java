package com.example.parqueaderoApi.service;

import com.example.parqueaderoApi.model.ParkingRequest;
import com.example.parqueaderoApi.model.ParkingResponse;
import java.util.List;

public interface ParkingService {
    void createParking(ParkingRequest parking);
    void exitParking(String placa);
    List<ParkingResponse> getAllParkingAvailable();

}

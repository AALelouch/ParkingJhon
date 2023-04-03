package com.example.parqueaderoApi.service;

import com.example.parqueaderoApi.model.CarRequest;
import com.example.parqueaderoApi.model.CarResponse;

import java.util.List;

public interface CarCrudService {
    void createCarro(CarRequest carro);
    void updateCarro(CarRequest carRequest, String placa);
    void deleteCarro(String placa);
    CarResponse getCarById(String placa);
    List<CarResponse> getAllCarros();
}

package com.example.parqueaderoApi.service;

import com.example.parqueaderoApi.entity.Carro;
import com.example.parqueaderoApi.model.CarRequest;
import com.example.parqueaderoApi.model.CarResponse;

import java.util.List;

public interface CarroCrudService {
    void createCarro(CarRequest carro);
    void updateCarro(CarRequest carRequest, String placa);
    void deleteCarro(String placa);
    Carro getCarroByPlaca(String placa);
    CarResponse setCarById(String placa);
    List<CarResponse> getAllCarros();
}

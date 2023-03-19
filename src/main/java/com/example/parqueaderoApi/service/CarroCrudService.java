package com.example.parqueaderoApi.service;

import com.example.parqueaderoApi.entity.Carro;
import com.example.parqueaderoApi.model.CarRequest;

import java.util.List;

public interface CarroCrudService {
    void createCarro(CarRequest carro);
    void updateCarro(Carro carro, String placa);
    void deleteCarro(String placa);
    Carro getCarroByPlaca(String placa);
    List<Carro> getAllCarros();
}

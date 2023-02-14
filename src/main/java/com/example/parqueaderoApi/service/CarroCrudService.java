package com.example.parqueaderoApi.service;

import com.example.parqueaderoApi.entity.Carro;

import java.util.List;

public interface CarroCrudService {
    void createCarro(Carro carro);
    void updateCarro(Carro carro, String placa);
    void deleteCarro(String placa);
    Carro getCarroByPlaca(String placa);
    List<Carro> getAllCarros();
}

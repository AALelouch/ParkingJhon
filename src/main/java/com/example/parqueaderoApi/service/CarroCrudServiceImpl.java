package com.example.parqueaderoApi.service;

import com.example.parqueaderoApi.entity.Carro;
import com.example.parqueaderoApi.entity.Parking;
import com.example.parqueaderoApi.exception.CarroNotFoundException;
import com.example.parqueaderoApi.model.CarRequest;
import com.example.parqueaderoApi.repository.CarroRepositorio;
import com.example.parqueaderoApi.repository.ParkingRepositorio;
import com.example.parqueaderoApi.service.util.PrecioPorDia;
import com.example.parqueaderoApi.service.util.PrecioPorHora;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class CarroCrudServiceImpl implements CarroCrudService{

    private final CarroRepositorio carroRepositorio;
    private final ParkingRepositorio parkingRepositorio;
    public CarroCrudServiceImpl(CarroRepositorio carroRepositorio, ParkingRepositorio parkingRepositorio) {
        this.carroRepositorio = carroRepositorio;
        this.parkingRepositorio = parkingRepositorio;
    }

    @Override
    public void createCarro(CarRequest carRequest) {
        Parking parking = parkingRepositorio.getFirstParkingAvailable().orElseThrow(()->new IllegalArgumentException("No se han encontrado parqueaderos"));
        parking.setEstado(false);
        parkingRepositorio.save(parking);
        carroRepositorio.save(Carro.builder().placa(carRequest.getPlaca()).modelo(carRequest.getModelo()).marca(carRequest.getMarca())
                .fechaDeEntrada(LocalDateTime.now()).parqueadero(parking).build());
    }

    @Override
    public void updateCarro(Carro carro, String placa) {
        carro.setPlaca(placa);
        carroRepositorio.save(carro);
    }

    @Override
    public void deleteCarro(String placa) {

    }

    @Override
    public Carro getCarroByPlaca(String placa) {
        return carroRepositorio.findById(placa)
                .orElseThrow(()->new CarroNotFoundException("Carro no encontrado"));
    }

    @Override
    public List<Carro> getAllCarros() {
        return carroRepositorio.findAll();
    }
}

package com.example.parqueaderoApi.service;

import com.example.parqueaderoApi.entity.Car;
import com.example.parqueaderoApi.entity.Parking;
import com.example.parqueaderoApi.exception.CarNotFoundException;
import com.example.parqueaderoApi.model.CarRequest;
import com.example.parqueaderoApi.model.CarResponse;
import com.example.parqueaderoApi.repository.CarroRepositorio;
import com.example.parqueaderoApi.repository.ParkingRepositorio;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class CarCrudServiceImpl implements CarCrudService {

    private final CarroRepositorio carroRepositorio;
    private final ParkingRepositorio parkingRepositorio;
    public CarCrudServiceImpl(CarroRepositorio carroRepositorio, ParkingRepositorio parkingRepositorio) {
        this.carroRepositorio = carroRepositorio;
        this.parkingRepositorio = parkingRepositorio;
    }

    @Override
    public void createCarro(CarRequest carRequest) {
        Parking parking = parkingRepositorio.getFirstParkingAvailable().orElseThrow(()->new IllegalArgumentException("No se han encontrado parqueaderos"));
        parking.setEstado(false);
        parkingRepositorio.save(parking);
        carroRepositorio.save(Car.builder().placa(carRequest.getPlaca()).modelo(carRequest.getModelo()).marca(carRequest.getMarca())
                .fechaDeEntrada(LocalDateTime.now()).parqueadero(parking).build());
    }

    @Override
    public void updateCarro(CarRequest carRequest, String placa) {
        carRequest.setPlaca(placa);
        carroRepositorio.save(Car.builder().placa(carRequest.getPlaca()).modelo(carRequest.getModelo()).marca(carRequest.getMarca())
                .fechaDeEntrada(LocalDateTime.now()).build());
    }

    @Override
    public void deleteCarro(String placa) {

    }

    @Override
    public CarResponse getCarById(String placa) {
        Car car = carroRepositorio.findById(placa)
                .orElseThrow(()->new CarNotFoundException("Carro no encontrado"));
        return CarResponse.builder().placa(car.getPlaca()).marca(car.getMarca()).modelo(car.getModelo())
                .fechaDeEntrada(car.getFechaDeEntrada()).fechaDeSalida(car.getFechaDeSalida())
                .horas(car.getHoras()).parqueadero(car.getParqueadero().getId())
                .build();
    }

    @Override
    public List<CarResponse> getAllCarros() {
        List<CarResponse> list = carroRepositorio.findAll().stream()
                .map(carro ->
                        CarResponse.builder()
                                .placa(carro.getPlaca()).marca(carro.getMarca()).modelo(carro.getModelo())
                                .fechaDeEntrada(carro.getFechaDeEntrada()).fechaDeSalida(carro.getFechaDeSalida())
                                .horas(carro.getHoras()).parqueadero(carro.getParqueadero().getId())
                                .build()).toList();
        return list;
    }
}

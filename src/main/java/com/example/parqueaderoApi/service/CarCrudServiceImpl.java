package com.example.parqueaderoApi.service;

import com.example.parqueaderoApi.entity.Car;
import com.example.parqueaderoApi.entity.Parking;
import com.example.parqueaderoApi.exception.CarNotFoundException;
import com.example.parqueaderoApi.exception.ParkingNotFoundException;
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
        Parking parking = parkingRepositorio.getFirstParkingAvailable().orElseThrow(()->new ParkingNotFoundException("Parking not found"));
        parking.setEstado(false);
        parkingRepositorio.save(parking);
        carroRepositorio.save(Car.builder().id(carRequest.getId()).model(carRequest.getModel()).brand(carRequest.getBrand())
                .entryDate(LocalDateTime.now()).parking(parking).build());
    }

    @Override
    public void updateCarro(CarRequest carRequest, String id) {
        carRequest.setId(id);
        carroRepositorio.save(Car.builder().id(carRequest.getId()).model(carRequest.getModel()).brand(carRequest.getBrand())
                .entryDate(LocalDateTime.now()).build());
    }

    @Override
    public void deleteCarro(String placa) {
        carroRepositorio.deleteById(placa);
    }

    @Override
    public CarResponse getCarById(String placa) {
        Car car = carroRepositorio.findById(placa)
                .orElseThrow(()->new CarNotFoundException("Carro no encontrado"));
        return CarResponse.builder().id(car.getId()).brand(car.getBrand()).model(car.getModel())
                .entryDate(car.getEntryDate()).departureDate(car.getDepartureDate())
                .hours(car.getHours()).parking(car.getParking().getId())
                .build();
    }

    @Override
    public List<CarResponse> getAllCarros() {
        List<CarResponse> list = carroRepositorio.findAll().stream()
                .map(carro ->
                        CarResponse.builder()
                                .id(carro.getId()).brand(carro.getBrand()).model(carro.getModel())
                                .entryDate(carro.getEntryDate()).departureDate(carro.getDepartureDate())
                                .hours(carro.getHours()).parking(carro.getParking().getId())
                                .build()).toList();
        return list;
    }
}

package com.example.parqueaderoApi.service;

import com.example.parqueaderoApi.entity.Car;
import com.example.parqueaderoApi.entity.Parking;
import com.example.parqueaderoApi.model.ParkingRequest;
import com.example.parqueaderoApi.model.ParkingResponse;
import com.example.parqueaderoApi.repository.CarroRepositorio;
import com.example.parqueaderoApi.repository.ParkingRepositorio;
import com.example.parqueaderoApi.service.util.PrecioPorDia;
import com.example.parqueaderoApi.service.util.PrecioPorHora;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class ParkingServiceImpl implements ParkingService{

    private final ParkingRepositorio parkingRepositorio;
    private final CarroRepositorio carroRepositorio;

    public ParkingServiceImpl(ParkingRepositorio parkingRepositorio, CarroRepositorio carroRepositorio) {
        this.parkingRepositorio = parkingRepositorio;
        this.carroRepositorio = carroRepositorio;
    }

    @Override
    public void createParking(ParkingRequest parkingRequest) {
        parkingRepositorio
            .save(Parking.builder().estado(parkingRequest.getStatus()).build());
    }

    @Override
    public void exitParking() {
        Parking parking = parkingRepositorio.exitParking().orElseThrow(()->new IllegalArgumentException("No hay ningun parking ocupado"));
        Car car = carroRepositorio.exitCarro().orElseThrow(()->new IllegalArgumentException("No hay ningun carro en el parqueadero"));

        parking.setEstado(true);
        parkingRepositorio.save(parking);

        car.setFechaDeSalida(LocalDateTime.now());
        Duration duration = Duration.between(car.getFechaDeEntrada(), car.getFechaDeSalida());

        car.setHoras((int) Math.ceil(duration
                .toHours())+1);

        if(car.getHoras()>=24){
            car.setHorasAPagar(PrecioPorDia
                    .obtenerPrecioPorDia(car));
        }else{
            car.setHorasAPagar(PrecioPorHora
                    .obtenerPrecioPorHora(car));
        }
        carroRepositorio.save(car);
    }

    @Override
    public List<ParkingResponse> getAllParkingAvailable() {
        List<ParkingResponse> list = parkingRepositorio.findAll().stream()
                .map(parking ->
                        ParkingResponse.builder()
                                .id(parking.getId())
                                .status(parking.getEstado())
                                .build()).toList();

        return list;
    }

}

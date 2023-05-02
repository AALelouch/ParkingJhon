package com.example.parqueaderoApi.service;

import com.example.parqueaderoApi.entity.Car;
import com.example.parqueaderoApi.entity.Parking;
import com.example.parqueaderoApi.exception.CarNotFoundException;
import com.example.parqueaderoApi.model.CarResponse;
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
import java.util.Map;
import java.util.Optional;

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
    public void exitParking(String placa) {
        Parking parking = parkingRepositorio.exitParking().orElseThrow(()->new IllegalArgumentException("No hay ningun parking ocupado"));
        Car car = carroRepositorio.findById(placa).orElseThrow(()->new CarNotFoundException("No existe el carro en el parqueadero"));
        carroRepositorio.exitCarro(placa);

        parking.setEstado(true);
        parkingRepositorio.save(parking);

        car.setDepartureDate(LocalDateTime.now());
        Duration duration = Duration.between(car.getEntryDate(), car.getDepartureDate());

        car.setHours((int) Math.ceil(duration
                .toHours())+1);

        Double durationDays = Math.ceil(duration
                .toDays());


        if(car.getHours()>=24){
            System.out.println(durationDays);
        }else{
            car.setHoursForPay(PrecioPorHora
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

package com.example.parqueaderoApi.service;

import com.example.parqueaderoApi.entity.Carro;
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
    public List<ParkingResponse> getParkingPorDisponibilidad(Boolean estado) {
        return null;
    }

    @Override
    public void createParking(ParkingRequest parkingRequest) {
        parkingRepositorio
            .save(Parking.builder().estado(parkingRequest.getStatus()).build());
    }

    @Override
    public void exitParking() {
        Parking parking = parkingRepositorio.exitParking().orElseThrow(()->new IllegalArgumentException("No hay ningun parking ocupado"));
        Carro carro = carroRepositorio.exitCarro().orElseThrow(()->new IllegalArgumentException("No hay ningun carro en el parqueadero"));

        parking.setEstado(true);
        parkingRepositorio.save(parking);

        carro.setFechaDeSalida(LocalDateTime.now());
        Duration duration = Duration.between(carro.getFechaDeEntrada(), carro.getFechaDeSalida());

        carro.setHoras((int) Math.ceil(duration
                .toHours())+1);

        if(carro.getHoras()>=24){
            carro.setHorasAPagar(PrecioPorDia
                    .obtenerPrecioPorDia(carro));
        }else{
            carro.setHorasAPagar(PrecioPorHora
                    .obtenerPrecioPorHora(carro));
        }
        carroRepositorio.save(carro);
    }

}

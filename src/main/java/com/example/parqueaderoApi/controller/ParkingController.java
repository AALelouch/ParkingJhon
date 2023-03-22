package com.example.parqueaderoApi.controller;

import com.example.parqueaderoApi.entity.Parking;
import com.example.parqueaderoApi.exception.ParkingNotFoundException;
import com.example.parqueaderoApi.model.ParkingRequest;
import com.example.parqueaderoApi.model.ParkingResponse;
import com.example.parqueaderoApi.repository.ParkingRepositorio;
import com.example.parqueaderoApi.service.ParkingServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@SecurityRequirement(name ="beareraAuth")
public class ParkingController {
    private final ParkingRepositorio parkingRepositorio;
    private final ParkingServiceImpl parkingService;

    public ParkingController(ParkingRepositorio parkingRepositorio, ParkingServiceImpl parkingService) {
        this.parkingRepositorio = parkingRepositorio;
        this.parkingService = parkingService;
    }
    @GetMapping("/getParking")
    public ResponseEntity<List<Parking>> getAllParkingAvailable(){
        List<ParkingResponse> list = parkingRepositorio.findAll().stream()
                .map(parking ->
                        ParkingResponse.builder()
                                .id(parking.getId())
                                .status(parking.getEstado())
                                .build()).toList();

        return ResponseEntity.ok(parkingRepositorio.getAllParkingAvailable());
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveParking(@RequestBody ParkingRequest parking){
        parkingService.createParking(parking);
    }

    @GetMapping("/exit")
    public void exitCarro(){
        parkingService.exitParking();
    }
}

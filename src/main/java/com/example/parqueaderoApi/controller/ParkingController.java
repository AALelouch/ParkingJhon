package com.example.parqueaderoApi.controller;


import com.example.parqueaderoApi.model.ParkingRequest;
import com.example.parqueaderoApi.model.ParkingResponse;
import com.example.parqueaderoApi.service.ParkingServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@SecurityRequirement(name ="bearerAuth")
public class ParkingController {
    private final ParkingServiceImpl parkingService;

    public ParkingController(ParkingServiceImpl parkingService) {
        this.parkingService = parkingService;
    }
    @GetMapping("/getParking")
    public List<ParkingResponse> getAllParkingAvailable(){
        return parkingService.getAllParkingAvailable();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveParking(@RequestBody ParkingRequest parking){
        parkingService.createParking(parking);
    }

    @GetMapping("/exit/{placa}")
    public void exitCarro(@PathVariable String placa){
        parkingService.exitParking(placa);
    }
}

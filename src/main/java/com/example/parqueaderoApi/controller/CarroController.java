package com.example.parqueaderoApi.controller;

import com.example.parqueaderoApi.entity.Carro;
import com.example.parqueaderoApi.entity.Parking;
import com.example.parqueaderoApi.repository.ParkingRepositorio;
import com.example.parqueaderoApi.service.CarroCrudService;
import com.example.parqueaderoApi.service.ParkingServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parqueadero")
public class CarroController {
    private final CarroCrudService carroCrudService;
    private final ParkingServiceImpl parkingService;

    private final ParkingRepositorio parkingRepositorio;

    public CarroController(CarroCrudService carroCrudService, ParkingServiceImpl parkingService, ParkingRepositorio parkingRepositorio) {
        this.carroCrudService = carroCrudService;

        this.parkingService = parkingService;

        this.parkingRepositorio = parkingRepositorio;
    }

    @GetMapping
    public ResponseEntity<List<Carro>> getAllCarros(){
        return ResponseEntity.ok(carroCrudService.getAllCarros());
    }

    @GetMapping("/obtenerParqueaderos")
    public ResponseEntity<Parking> getAllParkingAvailable(){
        return ResponseEntity.ok(parkingRepositorio.getAllParkingAvailable()
                .orElseThrow(()->new IllegalArgumentException("No hay parqueaderos disponibles")));
    }

    @PostMapping("/parking")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveParking(@RequestBody Parking parking){
        parkingService.createParking(parking);
    }
    @GetMapping("/carro/{placa}")
    public ResponseEntity<Carro> getCarroByPlaca(@PathVariable String placa){
        return ResponseEntity.ok(carroCrudService.getCarroByPlaca(placa));
    }
    @GetMapping("/salir")
    public void exitCarro(){
         parkingService.exitParking();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCarro(@RequestBody Carro carro){

        carroCrudService.createCarro(carro);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping("/update/{placa}")
    public void updateCarro(@RequestBody Carro carro, @PathVariable String placa){
        carroCrudService.updateCarro(carro, placa);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping("/delete/{placa}")
    public void deleteCarro(@RequestBody Carro carro, @PathVariable String placa){
        carroCrudService.deleteCarro(placa);
    }

}

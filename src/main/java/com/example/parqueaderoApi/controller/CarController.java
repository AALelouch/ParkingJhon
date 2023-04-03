package com.example.parqueaderoApi.controller;

import com.example.parqueaderoApi.model.CarRequest;
import com.example.parqueaderoApi.model.CarResponse;
import com.example.parqueaderoApi.repository.CarroRepositorio;
import com.example.parqueaderoApi.service.CarCrudServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
@SecurityRequirement(name ="bearerAuth")
public class CarController {
    private final CarCrudServiceImpl carroCrudService;
    private final CarroRepositorio carroRepositorio;


    public CarController(CarCrudServiceImpl carroCrudService, CarroRepositorio carroRepositorio) {
        this.carroCrudService = carroCrudService;
        this.carroRepositorio = carroRepositorio;
    }

    @GetMapping
    public List<CarResponse> getAllCarros(){
        return carroCrudService.getAllCarros();
    }

    @GetMapping("/carro/{placa}")
    public CarResponse getCarroByPlaca(@PathVariable String placa){
        return carroCrudService.getCarById(placa);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCarro(@RequestBody CarRequest carro){
        carroCrudService.createCarro(carro);
    }

    @PatchMapping("/update/{placa}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCarro(@RequestBody CarRequest carro, @PathVariable String placa){
        carroCrudService.updateCarro(carro, placa);
    }

    @DeleteMapping("/delete/{placa}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCarro(@RequestBody CarRequest carro, @PathVariable String placa){
        carroCrudService.deleteCarro(placa);
    }



}

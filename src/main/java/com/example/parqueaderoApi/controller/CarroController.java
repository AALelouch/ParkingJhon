package com.example.parqueaderoApi.controller;

import com.example.parqueaderoApi.entity.Carro;
import com.example.parqueaderoApi.model.CarRequest;
import com.example.parqueaderoApi.service.CarroCrudService;
import com.example.parqueaderoApi.service.CarroCrudServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarroController {
    private final CarroCrudServiceImpl carroCrudService;

    public CarroController(CarroCrudServiceImpl carroCrudService) {
        this.carroCrudService = carroCrudService;
    }

    @GetMapping
    public ResponseEntity<List<Carro>> getAllCarros(){
        return ResponseEntity.ok(carroCrudService.getAllCarros());
    }

    @GetMapping("/carro/{placa}")
    public ResponseEntity<Carro> getCarroByPlaca(@PathVariable String placa){
        return ResponseEntity.ok(carroCrudService.getCarroByPlaca(placa));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCarro(@RequestBody CarRequest carro){
        carroCrudService.createCarro(carro);
    }

    @PatchMapping("/update/{placa}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCarro(@RequestBody Carro carro, @PathVariable String placa){
        carroCrudService.updateCarro(carro, placa);
    }

    @DeleteMapping("/delete/{placa}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCarro(@RequestBody Carro carro, @PathVariable String placa){
        carroCrudService.deleteCarro(placa);
    }

}

package com.example.parqueaderoApi.controller;

import com.example.parqueaderoApi.entity.Carro;
import com.example.parqueaderoApi.model.CarRequest;
import com.example.parqueaderoApi.model.CarResponse;
import com.example.parqueaderoApi.repository.CarroRepositorio;
import com.example.parqueaderoApi.service.CarroCrudServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
@SecurityRequirement(name ="bearerAuth")
public class CarroController {
    private final CarroCrudServiceImpl carroCrudService;
    private final CarroRepositorio carroRepositorio;

    public CarroController(CarroCrudServiceImpl carroCrudService, CarroRepositorio carroRepositorio) {
        this.carroCrudService = carroCrudService;
        this.carroRepositorio = carroRepositorio;
    }

    @GetMapping
    public ResponseEntity<List<Carro>> getAllCarros(){
        List<CarResponse> list = carroRepositorio.findAll().stream()
                .map(carro ->
                        CarResponse.builder()
                                .placa(carro.getPlaca()).marca(carro.getMarca()).modelo(carro.getModelo())
                                .fechaDeEntrada(carro.getFechaDeEntrada()).fechaDeSalida(carro.getFechaDeSalida())
                                .horas(carro.getHoras()).parqueadero(carro.getParqueadero())
                                .build()).toList();
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

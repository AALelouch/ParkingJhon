package com.example.parqueaderoApi.model;

import com.example.parqueaderoApi.entity.Parking;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CarResponse {
    private String placa;
    private String modelo;
    private String marca;
    private LocalDateTime fechaDeEntrada;
    private LocalDateTime fechaDeSalida;
    private int horas;
    private int horasAPagar;
    private Long parqueadero;
}

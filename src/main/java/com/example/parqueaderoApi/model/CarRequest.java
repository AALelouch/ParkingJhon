package com.example.parqueaderoApi.model;

import lombok.Data;

@Data
public class CarRequest {
    private String placa;
    private String modelo;
    private String marca;
}

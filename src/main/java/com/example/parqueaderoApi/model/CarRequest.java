package com.example.parqueaderoApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarRequest {
    private String id;
    private String model;
    private String brand;
}

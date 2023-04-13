package com.example.parqueaderoApi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class CarResponse {
    private String id;
    private String model;
    private String brand;
    private LocalDateTime entryDate;
    private LocalDateTime departureDate;
    private int hours;
    private int hoursForPay;
    private Long parking;
}

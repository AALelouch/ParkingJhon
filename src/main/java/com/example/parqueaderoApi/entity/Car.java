package com.example.parqueaderoApi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "car")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Car {
    @Id
    private String id;

    private String model;

    private String brand;

    private LocalDateTime entryDate;

    private LocalDateTime departureDate;

    private int hours;

    private int hoursForPay;

    @OneToOne
    @JoinColumn(name = "idSlot")
    private Parking parking;

}

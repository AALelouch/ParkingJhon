package com.example.parqueaderoApi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "carro")
public class Carro {
    @Id
    @NotBlank
    private String placa;
    @NotBlank
    private String modelo;
    @NotBlank
    private String marca;
    @NotBlank
    private LocalDateTime fechaDeEntrada;
    @NotBlank
    private LocalDateTime fechaDeSalida;
    @NotBlank
    private int horas;
    @NotBlank
    private int horasAPagar;

    @OneToOne
    @JoinColumn(name = "idSlot")
    private Parking parqueadero;

}

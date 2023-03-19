package com.example.parqueaderoApi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "carro")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Carro {
    @Id
    private String placa;

    private String modelo;

    private String marca;

    private LocalDateTime fechaDeEntrada;

    private LocalDateTime fechaDeSalida;

    private int horas;

    private int horasAPagar;

    @OneToOne
    @JoinColumn(name = "idSlot")
    private Parking parqueadero;

}

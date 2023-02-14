package com.example.parqueaderoApi.service.util;

import com.example.parqueaderoApi.entity.Carro;

import java.time.Duration;
import java.time.LocalDateTime;

public class PrecioPorDia {
    public static int obtenerPrecioPorDia(Carro carro){

        int valorDia = 44000;
        int valorAPagar;

        LocalDateTime fechaEntrada = carro.getFechaDeEntrada();
        LocalDateTime fechaSalida = carro.getFechaDeSalida();
        Duration duration = Duration.between(fechaEntrada, fechaSalida);
        int dias = (int) (duration.toDays());

        if(dias<=2){
            valorAPagar = valorDia * dias;
        }else{
            valorAPagar = valorDia *(dias-2);
        }

        return valorAPagar;
    }
}

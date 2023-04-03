package com.example.parqueaderoApi.service.util;

import com.example.parqueaderoApi.entity.Car;

import java.time.Duration;
import java.time.LocalDateTime;

public class PrecioPorDia {
    public static int obtenerPrecioPorDia(Car car){

        int valorDia = 44000;
        int valorAPagar;

        LocalDateTime fechaEntrada = car.getFechaDeEntrada();
        LocalDateTime fechaSalida = car.getFechaDeSalida();
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

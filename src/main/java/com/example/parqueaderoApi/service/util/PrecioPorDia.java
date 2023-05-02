package com.example.parqueaderoApi.service.util;

import com.example.parqueaderoApi.config.Generated;
import com.example.parqueaderoApi.entity.Car;

import java.time.Duration;
import java.time.LocalDateTime;

public class PrecioPorDia {

    @Generated
    public PrecioPorDia() {
    }

    public static int obtenerPrecioPorDia(Car car){

        int valorDia = 44000;
        int valorAPagar;

        LocalDateTime fechaEntrada = car.getEntryDate();
        LocalDateTime fechaSalida = car.getDepartureDate();
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

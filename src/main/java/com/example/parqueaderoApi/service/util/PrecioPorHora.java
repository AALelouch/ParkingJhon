package com.example.parqueaderoApi.service.util;

import com.example.parqueaderoApi.config.Generated;
import com.example.parqueaderoApi.entity.Car;

public class PrecioPorHora {

    @Generated
    public PrecioPorHora() {
    }

    public static int obtenerPrecioPorHora(Car car){
        int precioPorHora = 2000;
        int pago;
        if(car.getHours()<=2){
            pago = precioPorHora* car
                    .getHours();
        }else{
            pago = precioPorHora*(car
                    .getHours()-2
            );
        }
        return pago;
    }
}

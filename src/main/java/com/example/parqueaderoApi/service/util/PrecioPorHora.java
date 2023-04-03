package com.example.parqueaderoApi.service.util;

import com.example.parqueaderoApi.entity.Car;

public class PrecioPorHora {
    public static int obtenerPrecioPorHora(Car car){
        int precioPorHora = 2000;
        int pago;
        if(car.getHoras()<=2){
            pago = precioPorHora* car
                    .getHoras();
        }else{
            pago = precioPorHora*(car
                    .getHoras()-2
            );
        }
        return pago;
    }
}

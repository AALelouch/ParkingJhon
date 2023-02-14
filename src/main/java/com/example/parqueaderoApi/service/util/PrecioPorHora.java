package com.example.parqueaderoApi.service.util;

import com.example.parqueaderoApi.entity.Carro;

public class PrecioPorHora {
    public static int obtenerPrecioPorHora(Carro carro){
        int precioPorHora = 2000;
        int pago;
        if(carro.getHoras()<=2){
            pago = precioPorHora*carro
                    .getHoras();
        }else{
            pago = precioPorHora*(carro
                    .getHoras()-2
            );
        }
        return pago;
    }
}

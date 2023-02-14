package com.example.parqueaderoApi.exception;

public class CarroNotFoundException extends RuntimeException{
    public CarroNotFoundException(String message){ super(message);}
}

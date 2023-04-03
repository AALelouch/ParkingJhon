package com.example.parqueaderoApi.exception;

public class CarNotFoundException extends RuntimeException{
    public CarNotFoundException(String message){ super(message);}
}

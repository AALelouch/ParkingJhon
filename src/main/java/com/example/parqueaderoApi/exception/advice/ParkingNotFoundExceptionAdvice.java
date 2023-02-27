package com.example.parqueaderoApi.exception.advice;

import com.example.parqueaderoApi.exception.CarroNotFoundException;
import com.example.parqueaderoApi.exception.ParkingNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ParkingNotFoundExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(ParkingNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleParkingNotFoundException(ParkingNotFoundException ex){return ex.getMessage();}
}

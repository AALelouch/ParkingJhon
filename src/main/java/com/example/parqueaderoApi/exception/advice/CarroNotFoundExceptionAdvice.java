package com.example.parqueaderoApi.exception.advice;

import com.example.parqueaderoApi.exception.CarroNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CarroNotFoundExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(CarroNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleCarroNotFoundExcepction(CarroNotFoundException ex){ return ex.getMessage(); }

}

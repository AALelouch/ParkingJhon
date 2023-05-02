package com.example.parqueaderoApi.service;

import com.example.parqueaderoApi.entity.Car;
import com.example.parqueaderoApi.entity.Parking;
import com.example.parqueaderoApi.model.ParkingRequest;
import com.example.parqueaderoApi.model.ParkingResponse;
import com.example.parqueaderoApi.repository.CarroRepositorio;
import com.example.parqueaderoApi.repository.ParkingRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class ParkingServiceImplTest {
    @Mock
    private CarroRepositorio carroRepositorio;
    @Mock
    private ParkingRepositorio parkingRepositorio;

    private ParkingService parkingService;

    @BeforeEach
    void init() throws Exception{
        MockitoAnnotations.openMocks(this).close();
        parkingService = new ParkingServiceImpl(
                parkingRepositorio,
                carroRepositorio
        );
    }

    @Test
    void givenParkingWhenCreateParkingIsCalledThenParkingCreated() {
        //Arrange
        ParkingRequest parkingRequest = new ParkingRequest(1L,true);

        //Act
        parkingService.createParking(parkingRequest);

        //Assert
        Mockito.verify(parkingRepositorio, Mockito.times(1)).save(any(Parking.class));

    }

    @Test
    void toStablishTheStatusOfParkingWithTrue() {
        //Arrange
        Parking parking = new Parking(1l, true);
        Mockito.when(parkingRepositorio.exitParking()).thenReturn(Optional.of(parking));

        Car car = new Car("ADR-85G", "2023", "Yamaha", LocalDateTime.now(), LocalDateTime.now(), 6, 10, parking);
        Car carTwo = new Car("ZGZ-09C", "2023", "Yamaha", LocalDateTime.of(2023, Month.APRIL, 20,13,0), LocalDateTime.now(), 32, 32, parking);
        Car carThree = new Car("FRT-07P", "2023", "Yamaha", LocalDateTime.of(2023, Month.APRIL, 20,13,0), LocalDateTime.of(2023, Month.APRIL, 20,13,0), 25, 25, parking);

        Mockito.when(carroRepositorio.exitCarro("ADR-85G")).thenReturn(Optional.of(car));
        Mockito.when(carroRepositorio.findById("ADR-85G")).thenReturn(Optional.of(car));
        Mockito.when(carroRepositorio.findById("ZGZ-09C")).thenReturn(Optional.of(carTwo));
        Mockito.when(carroRepositorio.findById("FRT-07P")).thenReturn(Optional.of(carThree));

        //Act
        parkingService.exitParking("ADR-85G");
        parkingService.exitParking("ZGZ-09C");
        parkingService.exitParking("FRT-07P");

        //Assert
        Mockito.verify(parkingRepositorio, Mockito.times(3)).save(any(Parking.class));
        Mockito.verify(carroRepositorio, Mockito.times(3)).save(any(Car.class));


    }

    @Test
    void getAllParkingAvailable() {
        //Arrange
        Parking parking = new Parking(1l, true);
        Parking parkingTwo = new Parking(2l, true);
        Parking parkingThree = new Parking(3l, false);

        List<Parking> list = new ArrayList<>();
        list.add(parking);
        list.add(parkingTwo);
        list.add(parkingThree);

        Mockito.when(parkingRepositorio.findAll()).thenReturn(list);

        ParkingResponse parkingResponse = new ParkingResponse(1L, true);
        ParkingResponse parkingResponseTwo = new ParkingResponse(2L, true);
        ParkingResponse parkingResponseThree = new ParkingResponse(3L, false);

        List<ParkingResponse> listExpect = new ArrayList<>();
        listExpect.add(parkingResponse);
        listExpect.add(parkingResponseTwo);
        listExpect.add(parkingResponseThree);

        //Act
        List<ParkingResponse> listResult = parkingService.getAllParkingAvailable();

        //Act
        assertEquals(listExpect, listResult);

    }
}
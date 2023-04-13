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
        ParkingRequest parkingRequest = new ParkingRequest(true);

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
        Mockito.when(carroRepositorio.exitCarro()).thenReturn(Optional.of(car));

        //Act
        parkingService.exitParking();

        //Assert
        Mockito.verify(parkingRepositorio, Mockito.times(1)).save(any(Parking.class));
        Mockito.verify(carroRepositorio, Mockito.times(1)).save(any(Car.class));


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
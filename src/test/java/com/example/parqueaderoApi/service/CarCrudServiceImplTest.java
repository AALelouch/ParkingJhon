package com.example.parqueaderoApi.service;

import com.example.parqueaderoApi.entity.Car;
import com.example.parqueaderoApi.entity.Parking;
import com.example.parqueaderoApi.model.CarRequest;
import com.example.parqueaderoApi.model.CarResponse;
import com.example.parqueaderoApi.repository.CarroRepositorio;
import com.example.parqueaderoApi.repository.ParkingRepositorio;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class CarCrudServiceImplTest {
    @Mock
    private CarroRepositorio carroRepositorio;
    @Mock
    private ParkingRepositorio parkingRepositorio;

    private CarCrudService carCrudService;

    @BeforeEach
    void init() throws Exception{
        MockitoAnnotations.openMocks(this).close();
        carCrudService = new CarCrudServiceImpl(
                carroRepositorio,
                parkingRepositorio
        );
    }

    @Test
    void givenCarRequestWhenCreateCarIsCalledThenCarCreated() {
        //Arrange
        CarRequest carRequest = new CarRequest("ADR-85G", "2023", "Yamaha");
        Parking parkingToReturnOfRepository = new Parking(1L, true);

        Mockito.when(parkingRepositorio.getFirstParkingAvailable()).thenReturn(Optional.of(parkingToReturnOfRepository));


        //Act
        carCrudService.createCarro(carRequest);

        //Assert
        Mockito.verify(carroRepositorio, Mockito.times(1)).save(any(Car.class));
    }

    @Test
    void givenACarRequestUpdateCartData() {
        //Arrange
        CarRequest carRequest = new CarRequest("ADR-85G", "2023", "Yamaha");
        String id = "ADR-85G";
        carRequest.setId(id);

        //Act
        carCrudService.updateCarro(carRequest, id);

        //Assert
        Mockito.verify(carroRepositorio, Mockito.times(1)).save(any(Car.class));
    }

    @Test
    void deleteACarWhenCarDeleteIsCalled() {
        //Arrange
        Parking parkingToReturnOfRepository = new Parking(1L, true);
        Mockito.when(parkingRepositorio.getFirstParkingAvailable()).thenReturn(Optional.of(parkingToReturnOfRepository));

        Car car = new Car("ADR-85G", "2023", "Yamaha", LocalDateTime.now(), LocalDateTime.now(), 6, 10, parkingToReturnOfRepository);
        String idToDelete="ADR-85G";

        //Act
        carCrudService.deleteCarro(idToDelete);

        //Assert
        Mockito.verify(carroRepositorio, Mockito.times(1)).deleteById(idToDelete);
    }

    @Test
    void givenACarResponseWheCarIsCalledThenGetCarById() {
        //Arrange
        Parking parkingToReturnOfRepository = new Parking(1L, true);
        Mockito.when(parkingRepositorio.getFirstParkingAvailable()).thenReturn(Optional.of(parkingToReturnOfRepository));
        Car car = new Car("ZGZ-03F", "2010", "Honda",  LocalDateTime.of(2023, Month.JANUARY, 3,13,0), LocalDateTime.of(2023, Month.APRIL, 3,13,0), 2, 2, parkingToReturnOfRepository);
        String id = "ADR-85G";
        Mockito.when(carroRepositorio.findById(id)).thenReturn(Optional.of(car));

        CarResponse carResponseExpect = new CarResponse("ZGZ-03F", "2010", "Honda", LocalDateTime.of(2023, Month.JANUARY, 3,13,0), LocalDateTime.of(2023, Month.APRIL, 3,13,0),2, 2, 1L);

        //Act
        CarResponse carResponseResult = carCrudService.getCarById(id);

        //Assert
        assertEquals(carResponseExpect, carResponseResult);


    }

    @Test
    void givenAListOfCarWhenIsCalledThenGetAllCar() {
        //Arrange

        Parking parkingToReturnOfRepository = new Parking(1L, true);
        Mockito.when(parkingRepositorio.getFirstParkingAvailable()).thenReturn(Optional.of(parkingToReturnOfRepository));

        Car car = new Car("ADR-85G", "2023", "Yamaha", LocalDateTime.now(), LocalDateTime.now(), 6, 10, parkingToReturnOfRepository);
        Car carTwo = new Car("FGA-99G", "2023", "Honda", LocalDateTime.now(), LocalDateTime.now(), 6, 10, parkingToReturnOfRepository);
        Car carThree = new Car("PLA-55G", "2023", "Auteco", LocalDateTime.now(), LocalDateTime.now(), 6, 10, parkingToReturnOfRepository);

        List<Car> list = new ArrayList<>();
        list.add(car);
        list.add(carTwo);
        list.add(carThree);

        Mockito.when(carroRepositorio.findAll()).thenReturn(list);

        CarResponse carResponseExpect = new CarResponse("ZGZ-03F", "2010", "Honda", LocalDateTime.now(), LocalDateTime.now(),2, 2, 1L);
        CarResponse carResponseExpectTwo = new CarResponse("ZHG-08P", "2014", "Honda", LocalDateTime.now(), LocalDateTime.now(),2, 2, 1L);
        CarResponse carResponseExpectThree = new CarResponse("ZGZ-20F", "2012", "Honda", LocalDateTime.now(), LocalDateTime.now(),2, 2, 1L);

        List<CarResponse> listExpect = new ArrayList<>();
        listExpect.add(carResponseExpect);
        listExpect.add(carResponseExpectTwo);
        listExpect.add(carResponseExpectThree);

        //Act
        List<CarResponse> listResponse = carCrudService.getAllCarros();

        //Assert
        assertEquals(listExpect, listResponse);


    }
}
package com.example.parqueaderoApi.controller;

import com.example.parqueaderoApi.model.CarRequest;
import com.example.parqueaderoApi.model.CarResponse;
import com.example.parqueaderoApi.service.CarCrudServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CarCrudServiceImpl carCrudService;
    @Autowired
    private ObjectMapper objectMapper;

    
    //correct use of jwt to test endpoints
    @Test
    void getAllCarros() throws Exception{
        //Arrange
        CarResponse carResponseExpect = new CarResponse("ZGZ-03F", "2010", "Honda", LocalDateTime.now(), LocalDateTime.now(),2, 2, 1L);
        CarResponse carResponseExpectTwo = new CarResponse("ZHG-08P", "2014", "Honda", LocalDateTime.now(), LocalDateTime.now(),2, 2, 1L);
        CarResponse carResponseExpectThree = new CarResponse("ZGZ-20F", "2012", "Honda", LocalDateTime.now(), LocalDateTime.now(),2, 2, 1L);

        List<CarResponse> listExpect = new ArrayList<>();
        listExpect.add(carResponseExpect);
        listExpect.add(carResponseExpectTwo);
        listExpect.add(carResponseExpectThree);


        //When
        when(carCrudService.getAllCarros()).thenReturn(listExpect);

        //Assert
        mockMvc.perform(get("/car")
                            .with(jwt().authorities(new SimpleGrantedAuthority("test")))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(listExpect)));
    }

    @Test
    void getCarroByPlaca() throws Exception{
        //Arrange
        CarResponse carResponseExpect = new CarResponse("ZGZ-03F", "2010", "Honda", LocalDateTime.now(), LocalDateTime.now(),2, 2, 1L);

        //When
        when(carCrudService.getCarById(any(String.class))).thenReturn(carResponseExpect);

        //Assert
        mockMvc.perform(get("/car/carro/ZGZ-03F")
                        .with(jwt().authorities(new SimpleGrantedAuthority("test")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(carResponseExpect)));

    }

    @Test
    void saveCarro() throws Exception{
        //Arrange
        CarRequest carRequest = new CarRequest("ZGZ-09C", "2023", "Honda");

        //Act
        mockMvc.perform(post("/car")
                        .with(jwt().authorities(new SimpleGrantedAuthority("test")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    void updateCarro() throws Exception{

        //Arrange
        CarRequest carRequest = new CarRequest("ADR-85G", "2023", "Victory");

        //Act
        mockMvc.perform(patch("/car/update/ADR-85G")
                        .with(jwt().authorities(new SimpleGrantedAuthority("test")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carRequest)))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteCarro() throws Exception{

        //Arrange
        CarRequest carRequest = new CarRequest("ADR-85G", "2023", "Victory");

        //Act
        mockMvc.perform(delete("/car/delete/ADR-85G")
                        .with(jwt().authorities(new SimpleGrantedAuthority("test")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carRequest)))
                .andExpect(status().isNoContent());

    }
}
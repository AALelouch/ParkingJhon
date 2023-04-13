package com.example.parqueaderoApi.controller;

import com.example.parqueaderoApi.model.CarResponse;
import com.example.parqueaderoApi.service.CarCrudServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CarCrudServiceImpl carCrudService;
    @Autowired
    private ObjectMapper objectMapper;

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
        mockMvc.perform(get("/car/ZGZ-03F")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(carResponseExpect)));

    }

    @Test
    void saveCarro() throws Exception{
        this.mockMvc
                .perform(post("/car")
                        .contentType(APPLICATION_JSON)
                        .content("""
                                {
                                "id":1L,
                                "model" : "2022",
                                "brand" : "honda"
                                  }
                                """)
                ).andExpect(status().isCreated());
    }

    @Test
    void updateCarro() {
        //Arrange
    }

    @Test
    void deleteCarro() {
    }
}
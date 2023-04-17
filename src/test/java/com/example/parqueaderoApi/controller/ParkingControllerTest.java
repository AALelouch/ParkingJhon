package com.example.parqueaderoApi.controller;

import com.example.parqueaderoApi.model.ParkingRequest;
import com.example.parqueaderoApi.model.ParkingResponse;
import com.example.parqueaderoApi.service.ParkingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ParkingController.class)
class ParkingControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ParkingService parkingService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllParkingAvailable() throws Exception {
        //Arrange
        ParkingResponse parkingResponseExpect = new ParkingResponse(1L, true);
        ParkingResponse parkingResponseExpectTwo = new ParkingResponse(2L, false);
        ParkingResponse parkingResponseExpectThree = new ParkingResponse(3L, true);

        List<ParkingResponse> listExpect = new ArrayList<>();
        listExpect.add(parkingResponseExpect);
        listExpect.add(parkingResponseExpectTwo);
        listExpect.add(parkingResponseExpectThree);

        //When
        when(parkingService.getAllParkingAvailable()).thenReturn(listExpect);

        //Assert
        mockMvc.perform(get("/parking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(listExpect)));
    }

    @Test
    void saveParking() throws Exception{
        //Arrange
        ParkingRequest parkingRequest = new ParkingRequest(true);

        //Act
        mockMvc.perform(post("/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(parkingRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    void exitCarro() throws Exception{
        //Act
        mockMvc.perform(post("/exit"))
                .andExpect(status().isCreated());
    }
}
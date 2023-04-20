package com.example.parqueaderoApi.controller;

import com.example.parqueaderoApi.model.UserRequest;
import com.example.parqueaderoApi.model.UserResponse;
import com.example.parqueaderoApi.service.ParkingService;
import com.example.parqueaderoApi.service.UserRegisterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserRegisterService userRegisterService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllUsers() throws Exception{

        //Arrange
        UserResponse userResponse = new UserResponse(1L, "John", "johnbernalsierra@gmail.com", "1234");
        UserResponse userResponseTwo = new UserResponse(2L, "Jairo", "john@gmail.com", "1234");
        UserResponse userResponseThree = new UserResponse(3L, "Eric", "eric@gmail.com", "1234");

        List<UserResponse> listExpect = new ArrayList<>();
        listExpect.add(userResponse);
        listExpect.add(userResponseTwo);
        listExpect.add(userResponseThree);

        //When
        when(userRegisterService.getAllUsers()).thenReturn(listExpect);

        //Assert
        mockMvc.perform(get("/user/getUser")
                        .with(jwt().authorities(new SimpleGrantedAuthority("test")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(listExpect)));
    }

    @Test
    void registerAccountNewUser() throws Exception{
        //Arrange
        UserRequest userRequest = new UserRequest("John", "johnbernalsierra@gmail.com", "1234");

        //Act
        mockMvc.perform(post("/user/createUser")
                        .with(jwt().authorities(new SimpleGrantedAuthority("test")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated());
    }
}
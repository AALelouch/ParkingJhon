package com.example.parqueaderoApi.service;

import com.example.parqueaderoApi.entity.Parking;
import com.example.parqueaderoApi.entity.User;
import com.example.parqueaderoApi.model.UserRequest;
import com.example.parqueaderoApi.model.UserResponse;
import com.example.parqueaderoApi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class UserRegisterServiceImplTest {
    @Mock
    private UserRepository userRepository;

    private UserRegisterService userRegisterService;

    @BeforeEach
    void init()throws Exception{
        MockitoAnnotations.openMocks(this).close();
        userRegisterService = new UserRegisterServiceImpl(userRepository);
    }

    @Test
    void save() {
        //Arrange
        UserRequest userRequest = new UserRequest("John", "johnbernalsierra@gmail.com", "1234");

        //Act
        userRegisterService.save(userRequest);

        //Assert
        Mockito.verify(userRepository, Mockito.times(1)).save(any(User.class));
    }

    @Test
    void getAllUsers() {
        //Arrange
        User user = new User(1L, "John", "johnbernalsierra@gmail.com", "1234");
        User userTwo = new User(2L, "Jairo", "jjbernals@gmail.com", "1234");

        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(userTwo);

        Mockito.when(userRepository.findAll()).thenReturn(list);

        UserResponse userResponse = new UserResponse(1L,"John","johnbernalsierra@gmail.com", "1234");
        UserResponse userResponseTwo = new UserResponse(2L, "Jairo", "jjbernals@gmail.com", "1234");

        List<UserResponse> listExpect = new ArrayList<>();
        listExpect.add(userResponse);
        listExpect.add(userResponseTwo);

        //Act
        List<UserResponse> listResult =userRegisterService.getAllUsers();

        //Assert
        assertEquals(listExpect, listResult);


    }
}
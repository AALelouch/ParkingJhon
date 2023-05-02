package com.example.parqueaderoApi.security.config;

import com.example.parqueaderoApi.entity.User;
import com.example.parqueaderoApi.repository.UserRepository;
import com.example.parqueaderoApi.service.CarCrudServiceImpl;
import com.example.parqueaderoApi.service.UserDetailsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserDetailServiceImplTest {
    @Mock
    private UserRepository userRepository;
    private UserDetailsService userDetailsService;
    @BeforeEach
    void init() throws Exception{
        MockitoAnnotations.openMocks(this).close();
        userDetailsService = new UserDetailServiceImpl(
                userRepository
        );
    }

  //  @Test
   // void loadUserByUsername() {
        //Arrange
     //   User user = new User(1L, "John", "john@gmail.com","1234");
       // Mockito.when(userRepository.findByEmail("johhn@gmail.com")).thenReturn(Optional.of(user));

        //UserDetails userExpect = new User(1L, "John", "john@gmail.com","1234");

        //Act
//        UserDetails userResult = userDetailsService.loadUserByUsername("john@gmail.com");

        //Assert
  //      assertEquals(userExpect, userResult);
    //}
}
package com.example.parqueaderoApi.controller;


import com.example.parqueaderoApi.model.UserRequest;
import com.example.parqueaderoApi.model.UserResponse;
import com.example.parqueaderoApi.repository.UserRepository;
import com.example.parqueaderoApi.service.UserRegisterService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name ="bearerAuth")
public class UserController {
    private UserRegisterService userRegisterService;

    public UserController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @GetMapping("/getUser")
    public List<UserResponse> getAllUsers(){
        return userRegisterService.getAllUsers();
    }

    @PostMapping("/createUser")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccountNewUser(@RequestBody UserRequest user){
        userRegisterService.save(user);
    }
}

package com.example.parqueaderoApi.controller;

import com.example.parqueaderoApi.entity.Carro;
import com.example.parqueaderoApi.entity.User;
import com.example.parqueaderoApi.model.UserRequest;
import com.example.parqueaderoApi.service.UserRegister;
import com.example.parqueaderoApi.service.UserRegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserRegisterService userRegisterService;

    public UserController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userRegisterService.getAllUser());
    }

    @PostMapping("/createUser")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccountNewUser(@RequestBody UserRequest user){
        userRegisterService.save(user);
    }
}

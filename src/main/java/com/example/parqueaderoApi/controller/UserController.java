package com.example.parqueaderoApi.controller;

import com.example.parqueaderoApi.entity.User;
import com.example.parqueaderoApi.model.UserRequest;
import com.example.parqueaderoApi.model.UserResponse;
import com.example.parqueaderoApi.repository.UserRepository;
import com.example.parqueaderoApi.service.UserRegisterService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name ="beareraAuth")
public class UserController {
    private UserRegisterService userRegisterService;
    private final UserRepository userRepository;

    public UserController(UserRegisterService userRegisterService, UserRepository userRepository) {
        this.userRegisterService = userRegisterService;
        this.userRepository = userRepository;
    }

    @GetMapping("/getUser")
    public ResponseEntity<List<User>> getAllUsers(){
        List<UserResponse> list = userRepository.findAll().stream()
                .map(user ->
                        UserResponse.builder()
                                .id(user.getId()).name(user.getName())
                                .email(user.getEmail()).password(user.getPassword())
                                .build()).toList();

        return ResponseEntity.ok(userRegisterService.getAllUser());
    }

    @PostMapping("/createUser")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccountNewUser(@RequestBody UserRequest user){
        userRegisterService.save(user);
    }
}

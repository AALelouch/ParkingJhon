package com.example.parqueaderoApi.service;

import com.example.parqueaderoApi.model.UserRequest;
import com.example.parqueaderoApi.model.UserResponse;

import java.util.List;

public interface UserRegisterService {
     void save(UserRequest user);
     List<UserResponse> getAllUsers();
}

package com.example.parqueaderoApi.service;

import com.example.parqueaderoApi.entity.User;
import com.example.parqueaderoApi.model.UserRequest;

import java.util.List;

public interface UserRegisterService {
     void save(UserRequest user);
     List<User> getAllUser();
}

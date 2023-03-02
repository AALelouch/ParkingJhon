package com.example.parqueaderoApi.service;

import com.example.parqueaderoApi.entity.User;

import java.util.List;

public interface UserRegisterService {
     void save(User user);
     List<User> getAllUser();
}

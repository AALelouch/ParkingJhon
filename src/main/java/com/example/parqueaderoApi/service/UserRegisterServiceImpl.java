package com.example.parqueaderoApi.service;

import com.example.parqueaderoApi.entity.User;
import com.example.parqueaderoApi.model.UserRequest;
import com.example.parqueaderoApi.model.UserResponse;
import com.example.parqueaderoApi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRegisterServiceImpl implements UserRegisterService{

    private UserRepository userRepository;

    public UserRegisterServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(UserRequest userRequest) {
        userRepository.save(User.builder().name(userRequest.getName())
                .email(userRequest.getEmail()).password(userRequest.getPassword()).build());
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<UserResponse> list = userRepository.findAll().stream()
                .map(user ->
                        UserResponse.builder()
                                .id(user.getId()).name(user.getName())
                                .email(user.getEmail()).password(user.getPassword())
                                .build()).toList();
        return list;
    }
}

package com.example.parqueaderoApi.service;

import com.example.parqueaderoApi.entity.User;
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
    public void save(User user) {
         userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}

package com.example.parqueaderoApi.service;

import com.example.parqueaderoApi.entity.User;
import com.example.parqueaderoApi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterServiceImpl implements UserRegisterService{

    private UserRepository userRepository;

    public UserRegisterServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegister register) {
        User user = new User(register.getName(), register.getEmail(), register.getPassword());
        return userRepository.save(user);
    }
}

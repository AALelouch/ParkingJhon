package com.example.parqueaderoApi.controller;

import com.example.parqueaderoApi.service.UserRegister;
import com.example.parqueaderoApi.service.UserRegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class UserController {
    private UserRegisterService userRegisterService;

    public UserController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @ModelAttribute("user")
    public UserRegister returnNewUser(){
        return new UserRegister();
    }

    @GetMapping
    public String showFormRegister(){
        return "register";
    }

    @PostMapping
    public String registerAccountNewUser(@ModelAttribute("user") UserRegister userRegister){
        userRegisterService.save(userRegister);
        return "redirect:/register?complete";
    }
}

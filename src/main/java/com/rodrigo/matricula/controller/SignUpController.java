package com.rodrigo.matricula.controller;

import com.rodrigo.matricula.dto.UserDTO;
import com.rodrigo.matricula.mapper.UserMapper;
import com.rodrigo.matricula.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
@Api(value = "Sign Up")
public class SignUpController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/signup")
    public void signUp(@RequestBody UserDTO userDTO){
        userService.signUpUser(userMapper.convertFromUserDTO(userDTO));
    }
}

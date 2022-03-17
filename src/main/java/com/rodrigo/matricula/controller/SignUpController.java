package com.rodrigo.matricula.controller;

import com.rodrigo.matricula.dto.UserDTO;
import com.rodrigo.matricula.mapper.MatriculaMapper;
import com.rodrigo.matricula.services.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
@Api(value = "Sign Up")
public class SignUpController {

    @Autowired
    private UserService userService;

    @Autowired
    private MatriculaMapper matriculaMapper;

    @PostMapping("/signup")
    public void signUp(@RequestBody UserDTO userDTO){
        userService.signUpUser(matriculaMapper.convertFromUserDTO(userDTO));
    }
}

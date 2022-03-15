package com.rodrigo.matricula.mapper;

import com.rodrigo.matricula.dto.UserDTO;
import com.rodrigo.matricula.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Bean
    public UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;
    }

    @Bean
    public User convertFromUserDTO(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);

        return user;
    }
}

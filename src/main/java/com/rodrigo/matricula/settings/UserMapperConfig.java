package com.rodrigo.matricula.settings;

import com.rodrigo.matricula.mapper.UserMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public UserMapper coffeeMapper() {
        return new UserMapper();
    }
}

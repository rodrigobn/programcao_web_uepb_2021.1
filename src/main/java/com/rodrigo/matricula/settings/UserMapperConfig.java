package com.rodrigo.matricula.settings;

import com.rodrigo.matricula.mapper.MatriculaMapper;
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
    public MatriculaMapper coffeeMapper() {
        return new MatriculaMapper();
    }
}

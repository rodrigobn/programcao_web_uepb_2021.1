package com.rodrigo.matricula.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class StudentDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("function")
    private String function;
}

package com.ao.musunatech.demoapp.dtos.output;

import java.io.Serializable;
import java.time.LocalDate;

public record AutorDtoOutput(
        String biografia,
        String autorNome,
        LocalDate dataDeNascimento,
        String nacionalidade
) implements Serializable {
}
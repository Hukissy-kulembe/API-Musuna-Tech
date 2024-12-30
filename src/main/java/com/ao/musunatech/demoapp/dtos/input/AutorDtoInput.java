package com.ao.musunatech.demoapp.dtos.input;

import java.io.Serializable;
import java.time.LocalDate;

public record AutorDtoInput(
        Long id,
        String nome,
        String nacionalidade,
        LocalDate dataDeNascimento,
        String biografia
) implements Serializable {
}

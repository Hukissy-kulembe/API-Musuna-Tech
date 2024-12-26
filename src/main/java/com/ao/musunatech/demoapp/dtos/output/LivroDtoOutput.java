package com.ao.musunatech.demoapp.dtos.output;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public record LivroDtoOutput(
        Long id,
        String titulo,
        LocalDate anoDePublicacao,
        String isbn,
        List<String> genero,
        int numeroDePagina,
        String idioma,
        String sinopse,
        String capa
) implements Serializable {
}

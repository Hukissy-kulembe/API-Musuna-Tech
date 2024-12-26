package com.ao.musunatech.demoapp.dtos.output;

import com.ao.musunatech.demoapp.models.Genero;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record LivroDtoOutput(
        Long id,
        String titulo,
        LocalDate anoDePublicacao,
        String isbn,
        Set<Genero> genero,
        int numeroDePagina,
        String idioma,
        String sinopse,
        String capa
) implements Serializable {
}

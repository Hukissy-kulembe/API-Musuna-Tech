package com.ao.musunatech.demoapp.dtos.input;

import com.ao.musunatech.demoapp.models.Autor;
import com.ao.musunatech.demoapp.models.Editora;
import com.ao.musunatech.demoapp.models.Genero;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public record LivroDtoInput(
        Editora editora,
        Set<Autor> autores,
        Set<Genero> generos,
        String titulo,
        String isbn,
        LocalDate anoDePublicacao,
        int numeroDePagina,
        String idioma,
        String sinopse,
        String capa,
        String urlLivro
) implements Serializable {
}

package com.ao.musunatech.demoapp.services.uteis;

import com.ao.musunatech.demoapp.models.Autor;
import com.ao.musunatech.demoapp.models.Genero;

import java.util.Set;
import java.util.stream.Collectors;

public class Uteis {

    public static Set<String> autores(Set<Autor> autores) {
        return autores.stream()
                .map(x -> x.getAutorNome()).collect(Collectors.toSet());
    }

    public static Set<String> generos(Set<Genero> generos) {
        return generos.stream()
                .map(x -> x.getGeneroNome()).collect(Collectors.toSet());
    }
}

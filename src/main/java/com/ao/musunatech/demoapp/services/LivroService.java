package com.ao.musunatech.demoapp.services;

import com.ao.musunatech.demoapp.dtos.output.LivroDtoOutput;
import com.ao.musunatech.demoapp.models.Livro;

import java.util.stream.Stream;

public interface LivroService {

    LivroDtoOutput cadastrarLivro(Livro livro);

    LivroDtoOutput buscarPorId(Long id);

    void deletarPorId(Long id);

    Stream<Livro> buscarPorIsbn(String isbn);

    LivroDtoOutput buscarPorTitulo(String titulo);

}

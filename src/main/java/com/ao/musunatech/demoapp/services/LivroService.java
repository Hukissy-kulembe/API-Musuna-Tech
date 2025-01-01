package com.ao.musunatech.demoapp.services;

import com.ao.musunatech.demoapp.dtos.input.LivroDtoInput;
import com.ao.musunatech.demoapp.dtos.output.AutorDtoOutput;
import com.ao.musunatech.demoapp.dtos.output.LivroDtoOutput;
import com.ao.musunatech.demoapp.models.Autor;

import java.util.List;

public interface LivroService {

    LivroDtoOutput cadastrar(LivroDtoInput livroDtoInput);

    LivroDtoOutput buscarPorId(Long id);

    void deletarPorId(Long id);

    LivroDtoOutput buscarPorIsbn(String isbn);

    LivroDtoOutput buscarPorTitulo(String titulo);

    List<LivroDtoOutput> buscarTodos();

    LivroDtoOutput atualizar(Long id, LivroDtoInput livroDtoInput);

    List<AutorDtoOutput> listarAutores(String livro);

    List<LivroDtoOutput> buscarLivrosPorAutor(String nome);

    List<LivroDtoOutput> buscarLivrosPorGenero(String genero);

}

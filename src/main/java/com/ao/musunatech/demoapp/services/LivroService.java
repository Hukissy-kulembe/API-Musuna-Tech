package com.ao.musunatech.demoapp.services;

import com.ao.musunatech.demoapp.dtos.input.LivroDtoInput;
import com.ao.musunatech.demoapp.dtos.output.LivroDtoOutput;

import java.util.List;

public interface LivroService {

    LivroDtoOutput cadastrar(LivroDtoInput livroDtoInput);

    LivroDtoOutput buscarPorId(Long id);

    void deletarPorId(Long id);

    LivroDtoOutput buscarPorIsbn(String isbn);

    LivroDtoOutput buscarPorTitulo(String titulo);

    List<LivroDtoOutput> buscarTodos();

    LivroDtoOutput atualizar(Long id, LivroDtoInput livroDtoInput);

}

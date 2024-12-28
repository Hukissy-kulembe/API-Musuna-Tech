package com.ao.musunatech.demoapp.services;

import com.ao.musunatech.demoapp.dtos.input.GeneroDtoInput;
import com.ao.musunatech.demoapp.dtos.output.GeneroDtoOutput;

import java.util.List;

public interface GeneroService {

    GeneroDtoOutput cadastrar(GeneroDtoInput generoDtoInput);

    GeneroDtoOutput buscarPorId(Long id);

    GeneroDtoOutput buscarPorNome(String nome);

    List<GeneroDtoOutput> buscarTodos();

    GeneroDtoOutput atualizar(Long id, GeneroDtoInput generoDtoInput);

    void deletarPorId(Long id);

}

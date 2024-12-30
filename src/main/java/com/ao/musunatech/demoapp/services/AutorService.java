package com.ao.musunatech.demoapp.services;

import com.ao.musunatech.demoapp.dtos.input.AutorDtoInput;
import com.ao.musunatech.demoapp.dtos.output.AutorDtoOutput;

import java.util.List;

public interface AutorService {

    AutorDtoOutput cadastrar(AutorDtoInput autorDtoInput);
    AutorDtoOutput buscarPorId(Long id);
    void deletarPorId(Long id);
    List<AutorDtoOutput> listarTodos();
    AutorDtoOutput buscarPorNome(String nome);
    AutorDtoOutput atualizar(Long id, AutorDtoInput autorDtoInput);

}

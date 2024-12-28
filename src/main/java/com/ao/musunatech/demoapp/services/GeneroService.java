package com.ao.musunatech.demoapp.services;

import com.ao.musunatech.demoapp.dtos.input.GeneroDtoInput;
import com.ao.musunatech.demoapp.dtos.output.GeneroDtoOutput;
import com.ao.musunatech.demoapp.dtos.output.LivroDtoOutput;
import com.ao.musunatech.demoapp.models.Genero;
import com.ao.musunatech.demoapp.repositories.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Stream;

public interface GeneroService {

    GeneroDtoOutput cadastrarGenero(Genero genero);
    GeneroDtoOutput buscarPorId(Long id);
    GeneroDtoOutput buscarPorNome(String nome);
    List<GeneroDtoOutput> buscarTodos();

    GeneroDtoOutput atualizarGenero(Long id, GeneroDtoInput generoDtoInput);

    void deletarPorId(Long id);

}

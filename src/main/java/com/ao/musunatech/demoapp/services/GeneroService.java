package com.ao.musunatech.demoapp.services;

import com.ao.musunatech.demoapp.dtos.output.GeneroDtoOutput;
import com.ao.musunatech.demoapp.models.Genero;
import com.ao.musunatech.demoapp.repositories.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface GeneroService {

    GeneroDtoOutput cadastrarGenero(Genero genero);
    GeneroDtoOutput buscarPorId(Long id);
    void deletarPorId(Long id);

}

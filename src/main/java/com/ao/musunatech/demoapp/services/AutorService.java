package com.ao.musunatech.demoapp.services;

import com.ao.musunatech.demoapp.dtos.output.AutorDtoOutput;
import com.ao.musunatech.demoapp.models.Autor;

public interface AutorService {

    AutorDtoOutput cadastrarAutor(Autor autor);
    AutorDtoOutput buscarPorId(Long id);
    void deletarPorId(Long id);

}

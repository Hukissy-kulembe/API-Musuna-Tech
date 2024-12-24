package com.ao.musunatech.demoapp.services;

import com.ao.musunatech.demoapp.models.Autor;

public interface AutorService {

    Autor cadastrarAutor(Autor autor);
    Autor buscarPorId(Long id);
    void deletarPorId(Long id);

}

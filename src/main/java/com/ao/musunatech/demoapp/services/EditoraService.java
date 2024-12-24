package com.ao.musunatech.demoapp.services;

import com.ao.musunatech.demoapp.models.Editora;

public interface EditoraService {

    Editora cadastrarEditora(Editora editora);
    Editora buscarPorId(Long id);
    void deletarPorId(Long id);

}

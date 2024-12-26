package com.ao.musunatech.demoapp.services;

import com.ao.musunatech.demoapp.dtos.output.EditoraDtoOutput;
import com.ao.musunatech.demoapp.models.Editora;

public interface EditoraService {

    EditoraDtoOutput cadastrarEditora(Editora editora);
    EditoraDtoOutput buscarPorId(Long id);
    void deletarPorId(Long id);

}

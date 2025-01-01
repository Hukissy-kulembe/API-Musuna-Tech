package com.ao.musunatech.demoapp.services;

import com.ao.musunatech.demoapp.dtos.input.EditoraDtoInput;
import com.ao.musunatech.demoapp.dtos.output.EditoraDtoOutput;
import com.ao.musunatech.demoapp.dtos.output.LivroDtoOutput;

import java.util.List;

public interface EditoraService {

    EditoraDtoOutput cadastrarEditora(EditoraDtoInput editoraDtoInput);
    EditoraDtoOutput buscarPorId(Long id);
    void deletarPorId(Long id);
    EditoraDtoOutput buscarPorTitulo(String titulo);
    EditoraDtoOutput atualizar(Long id, EditoraDtoInput editoraDtoInput);
    List<EditoraDtoOutput> listarEditoras();
    List<LivroDtoOutput> buscarLivrosDeUmaEditora(String editora);

}

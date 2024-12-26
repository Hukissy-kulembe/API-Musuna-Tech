package com.ao.musunatech.demoapp.services.implementacao;

import com.ao.musunatech.demoapp.dtos.output.EditoraDtoOutput;
import com.ao.musunatech.demoapp.models.Editora;
import com.ao.musunatech.demoapp.repositories.EditoraRepository;
import com.ao.musunatech.demoapp.services.EditoraService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditoraImplementacao implements EditoraService {

    @Autowired
    private EditoraRepository editoraRepository;

    @Override
    public EditoraDtoOutput cadastrarEditora(Editora editora) {
         var a = editoraRepository.save(editora);
         EditoraDtoOutput editoraDtoOutput = new EditoraDtoOutput(
                 a.getNifCnpj(),
                 a.getEndereco(),
                 a.getEditoraNome()
         );
        return editoraDtoOutput;
    }

    @Override
    public EditoraDtoOutput buscarPorId(Long id) {
        var editora = editoraRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Editora não encontrada para o id: " + id));

        EditoraDtoOutput editoraDto = new EditoraDtoOutput(
          editora.getNifCnpj(),
          editora.getEndereco(),
          editora.getEditoraNome()
        );
        return editoraDto;
    }

    @Override
    public void deletarPorId(Long id) {
        editoraRepository.deleteById(id);
    }

}

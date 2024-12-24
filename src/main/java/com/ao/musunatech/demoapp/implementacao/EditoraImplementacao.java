package com.ao.musunatech.demoapp.implementacao;

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
    public Editora cadastrarEditora(Editora editora) {
        return editoraRepository.save(editora);
    }

    @Override
    public Editora buscarPorId(Long id) {
        return editoraRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Editora não encontrada para o id: " + id));
    }

    @Override
    public void deletarPorId(Long id) {
        editoraRepository.deleteById(id);
    }

}

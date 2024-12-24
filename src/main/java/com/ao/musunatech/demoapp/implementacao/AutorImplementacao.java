package com.ao.musunatech.demoapp.implementacao;

import com.ao.musunatech.demoapp.models.Autor;
import com.ao.musunatech.demoapp.repositories.AutorRepository;
import com.ao.musunatech.demoapp.services.AutorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorImplementacao implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public Autor cadastrarAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    public Autor buscarPorId(Long id) {
        return autorRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado para o id: " + id));
    }

    @Override
    public void deletarPorId(Long id) {
        autorRepository.deleteById(id);
    }
}

package com.ao.musunatech.demoapp.implementacao;

import com.ao.musunatech.demoapp.models.Livro;
import com.ao.musunatech.demoapp.repositories.LivroRepository;
import com.ao.musunatech.demoapp.services.LivroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroImplementacao implements LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public Livro cadastrarLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    @Override
    public Livro buscarPorId(Long id) {
        return livroRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado para o ID: " + id));
    }

    @Override
    public void deletarPorId(Long id) {
        livroRepository.deleteById(id);
    }

}

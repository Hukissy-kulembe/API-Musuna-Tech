package com.ao.musunatech.demoapp.services.implementacao;

import com.ao.musunatech.demoapp.dtos.output.GeneroDtoOutput;
import com.ao.musunatech.demoapp.models.Genero;
import com.ao.musunatech.demoapp.repositories.GeneroRepository;
import com.ao.musunatech.demoapp.services.GeneroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroImplementacao implements GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Override
    public GeneroDtoOutput cadastrarGenero(Genero genero) {
        var a = generoRepository.save(genero);
        GeneroDtoOutput generoDtoOutput = new GeneroDtoOutput(
                a.getId(),
                a.getGeneroNome(),
                a.getDescricao(),
                a.getCriadoEm(),
                a.getAtualizadoEm()
        );
        return generoDtoOutput;
    }

    @Override
    public GeneroDtoOutput buscarPorId(Long id) {
        var a = generoRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado para o id: " + id));
        GeneroDtoOutput generoDtoOutput = new GeneroDtoOutput(
                a.getId(),
                a.getGeneroNome(),
                a.getDescricao(),
                a.getCriadoEm(),
                a.getAtualizadoEm()
        );
        return generoDtoOutput;
    }

    @Override
    public void deletarPorId(Long id) {
        generoRepository.deleteById(id);
    }
}

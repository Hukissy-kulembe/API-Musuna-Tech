package com.ao.musunatech.demoapp.services.implementacao;

import com.ao.musunatech.demoapp.dtos.input.GeneroDtoInput;
import com.ao.musunatech.demoapp.dtos.output.GeneroDtoOutput;
import com.ao.musunatech.demoapp.models.Genero;
import com.ao.musunatech.demoapp.repositories.GeneroRepository;
import com.ao.musunatech.demoapp.repositories.LivroRepository;
import com.ao.musunatech.demoapp.services.GeneroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GeneroImplementacao implements GeneroService {

    @Autowired
    private GeneroRepository generoRepository;
    @Autowired
    private LivroRepository livroRepository;

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

    public GeneroDtoOutput buscarPorNome(String nome) {
        var a = generoRepository.findByName(nome)
                .orElseThrow(() -> new RuntimeException("Gênero não encontrado"));
        GeneroDtoOutput generoDtoOutput = new GeneroDtoOutput(
                a.getId(), a.getGeneroNome(), a.getDescricao(), a.getCriadoEm(), a.getAtualizadoEm()
        );
        return generoDtoOutput;
    }

    @Override
    public List<GeneroDtoOutput> buscarTodos() {
        List<Genero> list = generoRepository.findAll();

        return list.stream()
                .map(value -> new GeneroDtoOutput(
                        value.getId(),
                        value.getGeneroNome(),
                        value.getDescricao(),
                        value.getCriadoEm(),
                        value.getAtualizadoEm()))
                .toList();
    }

    @Override
    public GeneroDtoOutput atualizarGenero(Long id, GeneroDtoInput generoDtoInput) {
        Genero genero = generoRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genero não encontrado com ID: " + id));

        genero.setGeneroNome(generoDtoInput.generoNome());
        genero.setDescricao(generoDtoInput.descricao());
        genero.setAtualizadoEm(LocalDateTime.now());

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
    public void deletarPorId(Long id) {
        Optional<Genero> optionalGenero = generoRepository.findById(id);
        if (optionalGenero.isEmpty()) {
            throw new EntityNotFoundException("Gênero não encontrado para o id: " + id);
        }
        generoRepository.deleteById(id);
    }
}

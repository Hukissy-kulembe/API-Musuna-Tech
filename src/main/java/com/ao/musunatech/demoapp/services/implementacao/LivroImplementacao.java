package com.ao.musunatech.demoapp.services.implementacao;

import com.ao.musunatech.demoapp.dtos.output.LivroDtoOutput;
import com.ao.musunatech.demoapp.models.Livro;
import com.ao.musunatech.demoapp.repositories.LivroRepository;
import com.ao.musunatech.demoapp.services.LivroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class LivroImplementacao implements LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public LivroDtoOutput cadastrarLivro(Livro livro) {
        var a = livroRepository.save(livro);
        LivroDtoOutput livroDtoOutput = new LivroDtoOutput(
                a.getId(),
                a.getTitulo(),
                a.getAnoDePublicacao(),
                a.getIsbn(),
                a.getGeneros(),
                a.getNumeroDePagina(),
                a.getIdioma(),
                a.getSinopse(),
                a.getCapa()
        );
        return livroDtoOutput;
    }

    @Override
    public LivroDtoOutput buscarPorId(Long id) {

        Livro livro = livroRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado para o ID: " + id));

        LivroDtoOutput livroDto = new LivroDtoOutput(
                livro.getId(),
                livro.getTitulo(),
                livro.getAnoDePublicacao(),
                livro.getIsbn(),
                livro.getGeneros(),
                livro.getNumeroDePagina(),
                livro.getIdioma(),
                livro.getSinopse(),
                livro.getCapa()
        );

        return livroDto;
    }

    @Override
    public void deletarPorId(Long id) {
        livroRepository.deleteById(id);
    }

    @Override
    public Stream<Livro> buscarPorIsbn(String isbn) {
        return livroRepository.findAll()
                .stream()
                .filter(value -> value.getIsbn() == isbn);
    }

    @Override
    public LivroDtoOutput buscarPorTitulo(String titulo) {
        return null;
    }

}

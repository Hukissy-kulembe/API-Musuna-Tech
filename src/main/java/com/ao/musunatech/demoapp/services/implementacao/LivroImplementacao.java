package com.ao.musunatech.demoapp.services.implementacao;

import com.ao.musunatech.demoapp.dtos.input.LivroDtoInput;
import com.ao.musunatech.demoapp.dtos.output.AutorDtoOutput;
import com.ao.musunatech.demoapp.dtos.output.LivroDtoOutput;
import com.ao.musunatech.demoapp.models.Autor;
import com.ao.musunatech.demoapp.models.Livro;
import com.ao.musunatech.demoapp.repositories.AutorRepository;
import com.ao.musunatech.demoapp.repositories.GeneroRepository;
import com.ao.musunatech.demoapp.repositories.LivroRepository;
import com.ao.musunatech.demoapp.services.LivroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LivroImplementacao implements LivroService {

    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    public LivroImplementacao(LivroRepository livroRepository, AutorRepository autorRepository, GeneroRepository generoRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    @Override
    public LivroDtoOutput cadastrar(LivroDtoInput livroDtoInput) {
        Livro livro = new Livro(
                livroDtoInput.editora(),
                livroDtoInput.autores(),
                livroDtoInput.generos(),
                livroDtoInput.capa(),
                livroDtoInput.urlLivro(),
                livroDtoInput.sinopse(),
                livroDtoInput.idioma(),
                livroDtoInput.numeroDePagina(),
                livroDtoInput.anoDePublicacao(),
                livroDtoInput.isbn(),
                livroDtoInput.titulo()
        );

        var a = livroRepository.save(livro);

        LivroDtoOutput livroDtoOutput = new LivroDtoOutput(
                a.getId(),
                a.getTitulo(),
                a.getAnoDePublicacao(),
                a.getIsbn(),
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

        return new LivroDtoOutput(
                livro.getId(),
                livro.getTitulo(),
                livro.getAnoDePublicacao(),
                livro.getIsbn(),
                livro.getNumeroDePagina(),
                livro.getIdioma(),
                livro.getSinopse(),
                livro.getCapa()
        );
    }

    @Override
    public void deletarPorId(Long id) {
        Optional<Livro> livroOptional = livroRepository.findById(id);
        if (livroOptional.isEmpty()) {
            throw new EntityNotFoundException("Livro não encontrado para o ID: " + id);
        }
        livroRepository.deleteById(id);
    }

    @Override
    public LivroDtoOutput buscarPorIsbn(String isbn) {
        Livro livro = livroRepository.findByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException(""));
        return new LivroDtoOutput(
                livro.getId(),
                livro.getTitulo(),
                livro.getAnoDePublicacao(),
                livro.getIsbn(),
                livro.getNumeroDePagina(),
                livro.getIdioma(),
                livro.getSinopse(),
                livro.getCapa()
        );
    }

    @Override
    public LivroDtoOutput buscarPorTitulo(String titulo) {
        Livro livro = livroRepository.findByTitulo(titulo)
                .orElseThrow(() ->
                        new EntityNotFoundException("Livro não encontrado para o titulo: " + titulo));
        return new LivroDtoOutput(
                livro.getId(),
                livro.getTitulo(),
                livro.getAnoDePublicacao(),
                livro.getIsbn(),
                livro.getNumeroDePagina(),
                livro.getIdioma(),
                livro.getSinopse(),
                livro.getCapa()
        );
    }

    @Override
    public List<LivroDtoOutput> buscarTodos() {
        return livroRepository.findAll()
                .stream()
                .map(value -> new LivroDtoOutput(
                        value.getId(),
                        value.getTitulo(),
                        value.getAnoDePublicacao(),
                        value.getIsbn(),
                        value.getNumeroDePagina(),
                        value.getIdioma(),
                        value.getSinopse(),
                        value.getCapa()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public LivroDtoOutput atualizar(Long id, LivroDtoInput livroDtoInput) {
        Livro livro = livroRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado para o ID: " + id));

        livro.setTitulo(livroDtoInput.titulo());
        livro.setAutores(livroDtoInput.autores());
        livro.setCapa(livroDtoInput.capa());
        livro.setUrlLivro(livroDtoInput.urlLivro());
        livro.setEditora(livroDtoInput.editora());
        livro.setGeneros(livroDtoInput.generos());
        livro.setAnoDePublicacao(livroDtoInput.anoDePublicacao());
        livro.setIsbn(livroDtoInput.isbn());
        livro.setSinopse(livroDtoInput.sinopse());
        livro.setIdioma(livroDtoInput.idioma());
        livro.setNumeroDePagina(livro.getNumeroDePagina());

        var l = livroRepository.save(livro);

        return new LivroDtoOutput(
                l.getId(),
                l.getTitulo(),
                l.getAnoDePublicacao(),
                l.getIsbn(),
                l.getNumeroDePagina(),
                l.getIdioma(),
                l.getSinopse(),
                l.getCapa()
        );
    }

    @Override
    public List<AutorDtoOutput> listarAutores(String livro) {
        Livro livros = livroRepository.findByTitulo(livro)
                .orElseThrow(
                        () -> new EntityNotFoundException("Livro não encontrado para o titulo: " + livro)
                );

        return livros.getAutores()
                .stream()
                .map(value -> new AutorDtoOutput(
                        value.getId(),
                        value.getAutorNome(),
                        value.getBiografia(),
                        value.getDataDeNascimento(),
                        value.getNacionalidade()))
                .collect(Collectors.toList());
    }

    @Override
    public List<LivroDtoOutput> buscarLivrosPorAutor(String nome) {
        var autor = autorRepository.findByAutorNome(nome)
                .orElseThrow(
                        () -> new EntityNotFoundException("Nenhum livro associado ao autor: " + nome));

        return autor.getLivros().stream().map(value ->
                        new LivroDtoOutput(
                                value.getId(),
                                value.getTitulo(),
                                value.getAnoDePublicacao(),
                                value.getIsbn(),
                                value.getNumeroDePagina(),
                                value.getIdioma(), value.getSinopse(),
                                value.getIsbn()))
                .collect(Collectors
                        .toList());
    }

}

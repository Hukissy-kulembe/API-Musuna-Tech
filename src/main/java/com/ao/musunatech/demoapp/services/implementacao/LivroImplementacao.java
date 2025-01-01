package com.ao.musunatech.demoapp.services.implementacao;

import com.ao.musunatech.demoapp.dtos.input.LivroDtoInput;
import com.ao.musunatech.demoapp.dtos.output.AutorDtoOutput;
import com.ao.musunatech.demoapp.dtos.output.LivroDtoOutput;
import com.ao.musunatech.demoapp.models.Autor;
import com.ao.musunatech.demoapp.models.Editora;
import com.ao.musunatech.demoapp.models.Genero;
import com.ao.musunatech.demoapp.models.Livro;
import com.ao.musunatech.demoapp.repositories.AutorRepository;
import com.ao.musunatech.demoapp.repositories.EditoraRepository;
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
    private GeneroRepository generoRepository;
    private EditoraRepository editoraRepository;

    public LivroImplementacao(LivroRepository livroRepository,
                              AutorRepository autorRepository,
                              GeneroRepository generoRepository,
                              EditoraRepository editoraRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
        this.generoRepository = generoRepository;
        this.editoraRepository = editoraRepository;
    }

    /**Cadastrar Livro: Permitir a criação de um novo livro com atributos como título,autor(es), gênero(s), editora, ano de publicação, ISBN e demais atributos.
     *
     * @param livroDtoInput
     * @return
     */
    @Override
    public LivroDtoOutput cadastrar(LivroDtoInput livroDtoInput) {

        Set<Genero> generos = livroDtoInput.generos()
                .stream()
                .map(value -> generoRepository.findById(value).
                        orElseThrow(() ->
                                new EntityNotFoundException("ID não correspondente para um determinado gênero.")
                        )).collect(Collectors.toSet());

        Set<Autor> autors = livroDtoInput.autores()
                .stream()
                .map(value -> autorRepository.findById(value)
                        .orElseThrow(
                                () -> new EntityNotFoundException("ID: " + livroDtoInput.editora() + " não correspondente para um determinado gênero.")
                        )).collect(Collectors.toSet());

        Editora editora = editoraRepository
                .findById(livroDtoInput.editora())
                .orElseThrow(
                        () -> new EntityNotFoundException("ID: " + livroDtoInput.editora() + " não encontrado para uma determinada editora")
                );

        Livro livro = new Livro(
                editora,
                autors,
                generos,
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

    /**Buscar Livro por ID: Recuperar os detalhes de um livro com base no seu identificador único.
     *
     * @param id
     */
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

    /**Deletar Livro: Remover um livro do sistema.
     *
     * @param id
     */
    @Override
    public void deletarPorId(Long id) {
        Optional<Livro> livroOptional = livroRepository.findById(id);
        if (livroOptional.isEmpty()) {
            throw new EntityNotFoundException("Livro não encontrado para o ID: " + id);
        }
        livroRepository.deleteById(id);
    }

    /**Buscar Livro por ISBN: Recuperar os detalhes de um livro com base no seu isbn.
     *
     * @param isbn
     * @return
     */
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

    /**Buscar Livro por Título: Permitir buscar livros com base no título.
     * @param titulo
     * @return LivroDtoOutput
     */
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

    /**Listar Livros: Retornar uma lista de todos os livros cadastrados.
     *
     * @return
     */
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

    /**Atualizar Livro: Editar as informações de um livro existente.
     *
     * @param id
     * @param livroDtoInput
     * @return
     */
    @Override
    public LivroDtoOutput atualizar(Long id, LivroDtoInput livroDtoInput) {
        Livro livro = livroRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado para o ID: " + id));

        Set<Genero> generos = livroDtoInput.generos()
                .stream()
                .map(value -> generoRepository.findById(value)
                        .orElseThrow(() ->
                                new EntityNotFoundException("ID não correspondente para um determinado gênero.")
                        )).collect(Collectors.toSet());

        Set<Autor> autors = livroDtoInput.autores()
                .stream()
                .map(value -> autorRepository.findById(value)
                        .orElseThrow(
                                () -> new EntityNotFoundException("ID não correspondente para um determinado gênero.")
                        )).collect(Collectors.toSet());

        Editora editora = editoraRepository
                .findById(livroDtoInput.editora())
                .orElseThrow(
                        () -> new EntityNotFoundException("ID: " + livroDtoInput.editora() + " não encontrado para uma determinada editora")
                );

        livro.setTitulo(livroDtoInput.titulo());
        livro.setAutores(autors);
        livro.setCapa(livroDtoInput.capa());
        livro.setUrlLivro(livroDtoInput.urlLivro());
        livro.setEditora(editora);
        livro.setGeneros(generos);
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

    /**Listar todos os autores registrados;
     *
     * @param livro
     * @return
     */
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

    /**Buscar Livros por Autor: Listar todos os livros associados a um determinado autor.
     *
     * @param nome
     * @return
     */
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

    /**Buscar Livros por Gênero: Listar livros que pertençam a um ou mais gêneros específicos.
     *
     * @param genero
     * @return
     */
    @Override
    public List<LivroDtoOutput> buscarLivrosPorGenero(String genero) {
        var generos = generoRepository
                .findByName(genero)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum livro associado ao autor: " + genero));

        return generos.getLivros().stream().map(value ->
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

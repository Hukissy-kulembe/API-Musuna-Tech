package com.ao.musunatech.demoapp.controllers;

import com.ao.musunatech.demoapp.dtos.input.LivroDtoInput;
import com.ao.musunatech.demoapp.dtos.output.AutorDtoOutput;
import com.ao.musunatech.demoapp.dtos.output.LivroDtoOutput;
import com.ao.musunatech.demoapp.services.LivroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livro")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    /** Cadastrar novos livros;
     *
     * @param livroDtoInput
     * @return
     */
    @PostMapping
    public ResponseEntity<LivroDtoOutput> cadastrar(@RequestBody LivroDtoInput livroDtoInput) {
        var livroDtoOutput = livroService.cadastrar(livroDtoInput);
        return new ResponseEntity<>(livroDtoOutput, HttpStatus.CREATED);
    }

    /** Buscar livros pelo seu título;
     *
     * @param titulo
     * @return
     */
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<LivroDtoOutput> buscarPorTitulo(@PathVariable String titulo) {
        var livro = livroService.buscarPorTitulo(titulo);
        return new ResponseEntity<>(livro, HttpStatus.OK);
    }

    /** Listar todos os livros registrados na base de dados;
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<LivroDtoOutput>> listarTodos() {
        var livro = livroService.buscarTodos();
        return new ResponseEntity<>(livro, HttpStatus.OK);
    }

    /** Buscar livros pelo seu id;
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<LivroDtoOutput> buscarPorId(@PathVariable Long id) {
        var livro = livroService.buscarPorId(id);
        return new ResponseEntity<>(livro, HttpStatus.OK);
    }

    /** Buscar livro pelo seu número isbn;
     *
     * @param isbn
     * @return
     */
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<LivroDtoOutput> buscarPorIsbn(@PathVariable String isbn) {
        var livro = livroService.buscarPorIsbn(isbn);
        return new ResponseEntity<>(livro, HttpStatus.OK);
    }

    /** Listar autores de um determinado livro;
     *
     * @param livro
     * @return
     */
    @GetMapping("/autores/{livro}")
    public ResponseEntity<List<AutorDtoOutput>> listarAutores(@PathVariable String livro) {
        var autores = livroService.listarAutores(livro);
        return new ResponseEntity<>(autores, HttpStatus.OK);
    }

    /** Buscar livros por autores;
     *
     * @param autor
     * @return
     */
    @GetMapping("/livros/{autor}")
    public ResponseEntity<List<LivroDtoOutput>> buscarLivrosPorAutor(@PathVariable String autor) {
        var livros = livroService.buscarLivrosPorAutor(autor);
        return new ResponseEntity<>(livros, HttpStatus.OK);
    }

    /** Buscar livros por Gênero;
     *
     * @param genero
     * @return
     */
    @GetMapping("genero/{genero}")
    public ResponseEntity<List<LivroDtoOutput>> buscarLivrosPorGenero(@PathVariable String genero) {
        var livros = livroService.buscarLivrosPorGenero(genero);
        return new ResponseEntity<>(livros, HttpStatus.OK);
    }

    /** Deletar um livro registrado no banco de dados pelo seu id;
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        livroService.deletarPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /** Atualizar livro;
     *
     * @param id
     * @param livro
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<LivroDtoOutput> atualizarLivro(@PathVariable Long id, @RequestBody LivroDtoInput livro) {
        var livros = livroService.atualizar(id, livro);
        return new ResponseEntity<>(livros, HttpStatus.OK);
    }

}

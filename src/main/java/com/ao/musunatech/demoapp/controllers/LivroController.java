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

    @PostMapping
    public ResponseEntity<LivroDtoOutput> cadastrar(@RequestBody LivroDtoInput livroDtoInput) {
        var livroDtoOutput = livroService.cadastrar(livroDtoInput);
        return new ResponseEntity<>(livroDtoOutput, HttpStatus.CREATED);
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<LivroDtoOutput> buscarPorTitulo(@PathVariable String titulo) {
        var livro = livroService.buscarPorTitulo(titulo);
        return new ResponseEntity<>(livro, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<LivroDtoOutput>> listarTodos() {
        var livro = livroService.buscarTodos();
        return new ResponseEntity<>(livro, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDtoOutput> buscarPorId(@PathVariable Long id) {
        var livro = livroService.buscarPorId(id);
        return new ResponseEntity<>(livro, HttpStatus.OK);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<LivroDtoOutput> buscarPorIsbn(@PathVariable String isbn) {
        var livro = livroService.buscarPorIsbn(isbn);
        return new ResponseEntity<>(livro, HttpStatus.OK);
    }

    @GetMapping("/autores/{livro}")
    public ResponseEntity<List<AutorDtoOutput>> listarAutores(@PathVariable String livro) {
        var autores = livroService.listarAutores(livro);
        return new ResponseEntity<>(autores, HttpStatus.OK);
    }

    @GetMapping("/livros/{autor}")
    public ResponseEntity<List<LivroDtoOutput>> buscarLivrosPorAutor(@PathVariable String autor) {
        var livros = livroService.buscarLivrosPorAutor(autor);
        return new ResponseEntity<>(livros, HttpStatus.OK);
    }

    @GetMapping("genero/{genero}")
    public ResponseEntity<List<LivroDtoOutput>> buscarLivrosPorGenero(@PathVariable String genero) {
        var livros = livroService.buscarLivrosPorGenero(genero);
        return new ResponseEntity<>(livros, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        livroService.deletarPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDtoOutput> atualizarLivro(@PathVariable Long id, @RequestBody LivroDtoInput livro) {
        var livros = livroService.atualizar(id, livro);
        return new ResponseEntity<>(livros, HttpStatus.OK);
    }

}

package com.ao.musunatech.demoapp.controllers;

import com.ao.musunatech.demoapp.dtos.input.LivroDtoInput;
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

    /**
     * Get: buscarPorId() -> busca registros pelo campo ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LivroDtoOutput> buscarPorId(@PathVariable Long id) {
        var livroDtoOutput = livroService.buscarPorId(id);
        return new ResponseEntity<>(livroDtoOutput, HttpStatus.OK);
    }

    /**
     * Get: buscarPorTitulo(String titulo) -> está versão do Get nos permite buscar determintados
     * livros identificando ele pelo seu titulo.
     */
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<LivroDtoOutput> buscarPorTitulo(@PathVariable String titulo) {
        var livroDtoOutput = livroService.buscarPorTitulo(titulo);
        return new ResponseEntity<>(livroDtoOutput, HttpStatus.FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        livroService.deletarPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Get: buscarPorIsbn() -> o parâmetro lhe permite buscar um determinado
     * livro registrado no banco de dados a partir do campo "ISBN".
     */
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<LivroDtoOutput> buscarPorIsbn(@PathVariable String isbn) {
        var livroDtoOutput = livroService.buscarPorIsbn(isbn);
        return new ResponseEntity<>(livroDtoOutput, HttpStatus.OK);
    }

    /**
     * Get: buscarTodos() -> responsavel por pegar todos os registros
     * dentro da Tabela Livros registrados.
     */
    @GetMapping
    public ResponseEntity<List<LivroDtoOutput>> buscarTodos() {
        var list = livroService.buscarTodos();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<LivroDtoOutput> atualizar(@PathVariable Long id, @RequestBody LivroDtoInput livroDtoInput){
        var livroDtoOutput = livroService.atualizar(id, livroDtoInput);
        return new ResponseEntity<>(livroDtoOutput, HttpStatus.OK);
    }

}

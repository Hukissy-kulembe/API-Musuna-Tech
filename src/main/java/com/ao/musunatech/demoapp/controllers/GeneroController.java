package com.ao.musunatech.demoapp.controllers;

import com.ao.musunatech.demoapp.dtos.input.GeneroDtoInput;
import com.ao.musunatech.demoapp.dtos.output.GeneroDtoOutput;
import com.ao.musunatech.demoapp.dtos.output.LivroDtoOutput;
import com.ao.musunatech.demoapp.services.GeneroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genero")
public class GeneroController {

    private final GeneroService generoService;

    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    /**Buscar gênero pelo seu id;
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<GeneroDtoOutput> getGeneroById(@PathVariable Long id) {
        GeneroDtoOutput generoDtoOutput = generoService.buscarPorId(id);
        return new ResponseEntity<>(generoDtoOutput, HttpStatus.OK);
    }

    /**Buscar um determinado gênero pelo seu nome/titulo;
     *
     * @param nome
     * @return
     */
    @GetMapping("/nome/{nome}")
    public ResponseEntity<GeneroDtoOutput> buscarGeneroPeloNome(@PathVariable String nome) {
        GeneroDtoOutput generoDtoOutput = generoService.buscarPorNome(nome);
        return new ResponseEntity<>(generoDtoOutput, HttpStatus.OK);
    }

    /**Lista de todos os livros associados a um determinado gênero;
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<GeneroDtoOutput>> getAll(){
        var list = generoService.buscarTodos();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**Cadastrar gênero no banco de dados;
     *
     * @param generoDtoInput
     * @return
     */
    @PostMapping
    public ResponseEntity<GeneroDtoOutput> salvarGenero(@RequestBody GeneroDtoInput generoDtoInput) {
        GeneroDtoOutput generoDtoOutput = generoService.cadastrar(generoDtoInput);
        return new ResponseEntity<>(generoDtoOutput, HttpStatus.CREATED);
    }

    /**Atualizar um determinado gênero pelo seu id;
     *
     * @param id
     * @param generoDtoInput
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<GeneroDtoOutput> atualizarGenero(@PathVariable Long id, @RequestBody GeneroDtoInput generoDtoInput){
        var genero = generoService.atualizar(id, generoDtoInput);
        return new ResponseEntity<>(genero, HttpStatus.OK);
    }

    /**Deletar genero pelo seu id;
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGeneroPorId(@PathVariable Long id){
        generoService.deletarPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**Buscar livros de um genero;
     *
     * @param genero
     * @return
     */
    @GetMapping("/livro/{genero}")
    public ResponseEntity<List<LivroDtoOutput>> buscarLivrosDeUmGenero(@PathVariable String genero) {
        var livros = generoService.buscarLivrosDeUmGenero(genero);
        return new ResponseEntity<>(livros, HttpStatus.OK);
    }
}

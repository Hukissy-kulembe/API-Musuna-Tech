package com.ao.musunatech.demoapp.controllers;

import com.ao.musunatech.demoapp.dtos.input.GeneroDtoInput;
import com.ao.musunatech.demoapp.dtos.output.GeneroDtoOutput;
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

    /**
     * Get: getGeneroById(Long id) ->
     * pega um determinado Gênero pelo campo ID
     * */

    @GetMapping("/{id}")
    public ResponseEntity<GeneroDtoOutput> getGeneroById(@PathVariable Long id) {
        GeneroDtoOutput generoDtoOutput = generoService.buscarPorId(id);
        return new ResponseEntity<>(generoDtoOutput, HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<GeneroDtoOutput> getByName(@PathVariable String nome) {
        GeneroDtoOutput generoDtoOutput = generoService.buscarPorNome(nome);
        return new ResponseEntity<>(generoDtoOutput, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GeneroDtoOutput>> getAll(){
        var list = generoService.buscarTodos();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GeneroDtoOutput> salvarGenero(@RequestBody GeneroDtoInput generoDtoInput) {
        GeneroDtoOutput generoDtoOutput = generoService.cadastrar(generoDtoInput);
        return new ResponseEntity<>(generoDtoOutput, HttpStatus.CREATED);
    }

    /**
     * Put: atualizarGenero(Long id, GeneroDtoInput) ->
     * este método serve para atualizar o Gênero
     * no nosso banco de dados; apenas podem ser atualizados os campos:
     *      generoNome, descricao e atualizarEm
     * */

    @PutMapping("/{id}")
    public ResponseEntity<GeneroDtoOutput> atualizarGenero(@PathVariable Long id, @RequestBody GeneroDtoInput generoDtoInput){
        var genero = generoService.atualizar(id, generoDtoInput);
        return new ResponseEntity<>(genero, HttpStatus.OK);
    }

    /**
     * Delete: deletarGeneroPorId(Long id) ->
     *  deleta o Gênero pelo campo ID
     * */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGeneroPorId(@PathVariable Long id){
        generoService.deletarPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

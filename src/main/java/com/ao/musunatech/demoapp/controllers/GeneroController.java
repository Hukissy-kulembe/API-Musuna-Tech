package com.ao.musunatech.demoapp.controllers;

import com.ao.musunatech.demoapp.dtos.input.GeneroDtoInput;
import com.ao.musunatech.demoapp.dtos.output.GeneroDtoOutput;
import com.ao.musunatech.demoapp.models.Genero;
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

    @GetMapping("/id/{id}")
    public ResponseEntity<GeneroDtoOutput> getGeneroById(@PathVariable Long id) {
        GeneroDtoOutput generoDtoOutput = generoService.buscarPorId(id);
        return new ResponseEntity<>(generoDtoOutput, HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<GeneroDtoOutput> getByName(@PathVariable String nome) {
        GeneroDtoOutput generoDtoOutput = generoService.buscarPorNome(nome);
        return new ResponseEntity<>(generoDtoOutput, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GeneroDtoOutput>> getAll(){
        var list = generoService.buscarTodos();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GeneroDtoOutput> salvarGenero(@RequestBody GeneroDtoInput generoDtoInput) {
        GeneroDtoOutput generoDtoOutput = generoService.cadastrarGenero(new Genero(
                generoDtoInput.generoNome(),
                generoDtoInput.descricao(),
                generoDtoInput.criadoEm(),
                generoDtoInput.atualizadoEm()
        ));
        return new ResponseEntity<>(generoDtoOutput, HttpStatus.OK);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<GeneroDtoOutput> atualizarGenero(@PathVariable Long id, @RequestBody GeneroDtoInput generoDtoInput){
        var genero = generoService.atualizarGenero(id, generoDtoInput);
        GeneroDtoOutput generoDtoOutput = new GeneroDtoOutput(
                genero.id(),
                genero.generoNome(),
                genero.descricao(),
                genero.criadoEm(),
                genero.atualizadoEm()
        );
        return new ResponseEntity<>(generoDtoOutput, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletarGeneroPorId(@PathVariable Long id){
        generoService.deletarPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

package com.ao.musunatech.demoapp.controllers;

import com.ao.musunatech.demoapp.dtos.input.AutorDtoInput;
import com.ao.musunatech.demoapp.dtos.output.AutorDtoOutput;
import com.ao.musunatech.demoapp.dtos.output.LivroDtoOutput;
import com.ao.musunatech.demoapp.services.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autor")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @PostMapping
    public ResponseEntity<AutorDtoOutput> cadastrar(@RequestBody AutorDtoInput autorDtoInput){
         var autorDtoOutput = autorService.cadastrar(autorDtoInput);
         return new ResponseEntity<>(autorDtoOutput, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDtoOutput> buscarPorId(@PathVariable Long id){
        var autorDtoOutput = autorService.buscarPorId(id);
        return new ResponseEntity<>(autorDtoOutput, HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<AutorDtoOutput> buscarPorNome(@PathVariable String nome){
        var autorDtoOutput = autorService.buscarPorNome(nome);
        return new ResponseEntity<>(autorDtoOutput, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AutorDtoOutput>> buscarTodos(){
        var autorDtoOutput = autorService.listarTodos();
        return new ResponseEntity<>(autorDtoOutput, HttpStatus.OK);
    }

    @GetMapping("/livros/{autor}")
    public ResponseEntity<List<LivroDtoOutput>> buscarLivrosDeUmAutores(@PathVariable String autor) {
        var livros = autorService.buscarLivrosDeUmAutor(autor);
        return new ResponseEntity<>(livros, HttpStatus.OK);
    }

    @GetMapping("/autores/{autor}")
    public ResponseEntity<List<LivroDtoOutput>> buscarLivrosDeUmAutor(String autor) {
        var livros = autorService.buscarLivrosDeUmAutor(autor);
        return new ResponseEntity<>(livros, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        autorService.deletarPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

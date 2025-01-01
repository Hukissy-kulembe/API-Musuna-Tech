package com.ao.musunatech.demoapp.controllers;

import com.ao.musunatech.demoapp.dtos.input.EditoraDtoInput;
import com.ao.musunatech.demoapp.dtos.output.EditoraDtoOutput;
import com.ao.musunatech.demoapp.dtos.output.LivroDtoOutput;
import com.ao.musunatech.demoapp.services.EditoraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/editora")
public class EditoraController {

    private final EditoraService editoraService;

    public EditoraController(EditoraService editoraService) {
        this.editoraService = editoraService;
    }

    @PostMapping
    public ResponseEntity<EditoraDtoOutput> cadastrar(@RequestBody EditoraDtoInput editoraDtoInput) {
        var editoraDtoOutput = editoraService.cadastrarEditora(editoraDtoInput);
        return new ResponseEntity<>(editoraDtoOutput, HttpStatus.CREATED);
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<EditoraDtoOutput> buscarPorTitulo(@PathVariable String titulo) {
        var editoraDtoOutput = editoraService.buscarPorTitulo(titulo);
        return new ResponseEntity<>(editoraDtoOutput, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditoraDtoOutput> buscarPorId(@PathVariable Long id) {
        var editoraDtoOutput = editoraService.buscarPorId(id);
        return new ResponseEntity<>(editoraDtoOutput, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EditoraDtoOutput> atualizarEditora(@PathVariable Long id, @RequestBody EditoraDtoInput editoraDtoInput) {
        var editoraDtoOutput = editoraService.atualizar(id, editoraDtoInput);
        return new ResponseEntity<>(editoraDtoOutput, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEditora(@PathVariable Long id) {
        editoraService.deletarPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<EditoraDtoOutput>> listarEditoras() {
        var lista = editoraService.listarEditoras();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/editora/{editora}")
    public ResponseEntity<List<LivroDtoOutput>> buscarLivrosDeUmaEditora(@PathVariable String editora) {
        var listaLivros = editoraService.buscarLivrosDeUmaEditora(editora);
        return new ResponseEntity<>(listaLivros, HttpStatus.OK);
    }
}

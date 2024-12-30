package com.ao.musunatech.demoapp.services.implementacao;

import com.ao.musunatech.demoapp.dtos.input.EditoraDtoInput;
import com.ao.musunatech.demoapp.dtos.output.EditoraDtoOutput;
import com.ao.musunatech.demoapp.models.Editora;
import com.ao.musunatech.demoapp.repositories.EditoraRepository;
import com.ao.musunatech.demoapp.services.EditoraService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EditoraImplementacao implements EditoraService {

    @Autowired
    private EditoraRepository editoraRepository;

    @Override
    public EditoraDtoOutput cadastrarEditora(EditoraDtoInput editoraDtoInput) {
        var editora = new Editora(
                editoraDtoInput.nifCnpj(),
                editoraDtoInput.endereco(),
                editoraDtoInput.nome()
        );

        var a = editoraRepository.save(editora);

        EditoraDtoOutput editoraDtoOutput = new EditoraDtoOutput(
                a.getId(),
                a.getNifCnpj(),
                a.getEndereco(),
                a.getEditoraNome()
        );
        return editoraDtoOutput;
    }

    @Override
    public EditoraDtoOutput buscarPorId(Long id) {
        var editora = editoraRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Editora não encontrada para o id: " + id));

        EditoraDtoOutput editoraDto = new EditoraDtoOutput(
                editora.getId(),
                editora.getNifCnpj(),
                editora.getEndereco(),
                editora.getEditoraNome()
        );
        return editoraDto;
    }

    @Override
    public void deletarPorId(Long id) {
        var editora = editoraRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Editora não encontrada para ID: " + id));
        editoraRepository.deleteById(id);
    }

    @Override
    public EditoraDtoOutput buscarPorTitulo(String editoraNome) {
        var editora = editoraRepository
                .findByName(editoraNome).orElseThrow(() -> new EntityNotFoundException("Editora não encontrada para o titulo: " + editoraNome));

        return new EditoraDtoOutput(
                editora.getId(),
                editora.getNifCnpj(),
                editora.getEndereco(),
                editora.getEditoraNome()
        );
    }

    @Override
    public EditoraDtoOutput atualizar(Long id, EditoraDtoInput editoraDtoInput) {

        Editora editora = editoraRepository
                .findById(id).orElseThrow(() ->
                        new EntityNotFoundException("Editora não encontrada para o ID: " + id));

        editora.setEditoraNome(editoraDtoInput.nome());
        editora.setEndereco(editoraDtoInput.endereco());
        editora.setNifCnpj(editoraDtoInput.nifCnpj());

        var a = editoraRepository.save(editora);

        return new EditoraDtoOutput(
                a.getId(),
                a.getNifCnpj(),
                a.getEndereco(),
                a.getEditoraNome()
        );
    }

    @Override
    public List<EditoraDtoOutput> listarTodos() {
        var lista = editoraRepository.findAll();

        if (lista.isEmpty())
            throw new EntityNotFoundException("Não há registro na tabela Gênero");

        return lista.stream()
                .map(value -> new EditoraDtoOutput(
                        value.getId(),
                        value.getNifCnpj(),
                        value.getEndereco(),
                        value.getEditoraNome()
                )).collect(Collectors.toList());
    }

}

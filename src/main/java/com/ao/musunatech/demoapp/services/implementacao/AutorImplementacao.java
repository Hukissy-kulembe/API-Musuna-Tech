package com.ao.musunatech.demoapp.services.implementacao;

import com.ao.musunatech.demoapp.dtos.input.AutorDtoInput;
import com.ao.musunatech.demoapp.dtos.output.AutorDtoOutput;
import com.ao.musunatech.demoapp.models.Autor;
import com.ao.musunatech.demoapp.repositories.AutorRepository;
import com.ao.musunatech.demoapp.services.AutorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorImplementacao implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public AutorDtoOutput cadastrar(AutorDtoInput autorDtoInput) {
        var autor = new Autor();
        autor.setAutorNome(autorDtoInput.nome());
        autor.setBiografia(autorDtoInput.biografia());
        autor.setDataDeNascimento(autorDtoInput.dataDeNascimento());
        autor.setNacionalidade(autorDtoInput.nacionalidade());

        var a = autorRepository.save(autor);

        return new AutorDtoOutput(
                a.getId(),
                a.getBiografia(),
                a.getAutorNome(),
                a.getDataDeNascimento(),
                a.getNacionalidade()
        );
    }

    @Override
    public AutorDtoOutput buscarPorId(Long id) {
        var autor = autorRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado para o id: " + id));

        return new AutorDtoOutput(
                autor.getId(),
                autor.getBiografia(),
                autor.getAutorNome(),
                autor.getDataDeNascimento(),
                autor.getNacionalidade()
        );
    }

    @Override
    public void deletarPorId(Long id) {
        autorRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Autor não encontrado para o ID: " + id));
        autorRepository.deleteById(id);
    }

    @Override
    public List<AutorDtoOutput> listarTodos() {
        List<Autor> autors = autorRepository.findAll();

        if (autors.isEmpty()) {
            throw new EntityNotFoundException("Está vazia");
        }

        return autors.stream()
                .map(value -> new AutorDtoOutput(
                        value.getId(),
                        value.getBiografia(),
                        value.getAutorNome(),
                        value.getDataDeNascimento(),
                        value.getNacionalidade()
                )).collect(Collectors
                        .toList());
    }

    @Override
    public AutorDtoOutput buscarPorNome(String nome) {
        var autor = autorRepository.findByAutorNome(nome)
                .orElseThrow(() -> new
                        EntityNotFoundException("Autor não encontrado para o nome: " + nome));
        return new AutorDtoOutput(
                autor.getId(),
                autor.getBiografia(),
                autor.getAutorNome(),
                autor.getDataDeNascimento(),
                autor.getNacionalidade()
        );
    }

    @Override
    public AutorDtoOutput atualizar(Long id, AutorDtoInput autorDtoInput) {
        Autor autor = autorRepository
                .findById(id)
                .map(value -> new Autor(autorDtoInput.nome(),
                        autorDtoInput.biografia(),
                        autorDtoInput.dataDeNascimento(),
                        autorDtoInput.nacionalidade()))
                .orElseThrow(() ->
                        new EntityNotFoundException("Autor não encontrado para o ID: " + id));
        var a = autorRepository.save(autor);
        return new AutorDtoOutput(
                a.getId(),
                a.getBiografia(),
                a.getAutorNome(),
                a.getDataDeNascimento(),
                a.getNacionalidade()
        );
    }
}

package com.ao.musunatech.demoapp.services.implementacao;

import com.ao.musunatech.demoapp.dtos.output.AutorDtoOutput;
import com.ao.musunatech.demoapp.models.Autor;
import com.ao.musunatech.demoapp.repositories.AutorRepository;
import com.ao.musunatech.demoapp.services.AutorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorImplementacao implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public AutorDtoOutput cadastrarAutor(Autor autor) {
        //'a' valor responsável por alocar o objeto retornado;
        var a = autorRepository.save(autor);
        AutorDtoOutput autorDtoOutput = new AutorDtoOutput(
                a.getBiografia(),
                a.getAutorNome(),
                a.getDataDeNascimento(),
                a.getNacionalidade()
        );
        return autorDtoOutput;
    }

    @Override
    public AutorDtoOutput buscarPorId(Long id) {
         var autor = autorRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado para o id: " + id));

         AutorDtoOutput autorDtoOutput = new AutorDtoOutput(
                 autor.getBiografia(),
                 autor.getAutorNome(),
                 autor.getDataDeNascimento(),
                 autor.getNacionalidade()
         );
        return autorDtoOutput;
    }

    @Override
    public void deletarPorId(Long id) {
        autorRepository.deleteById(id);
    }
}

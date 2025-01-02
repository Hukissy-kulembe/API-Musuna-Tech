package com.ao.musunatech.demoapp.services.implementacao;

import com.ao.musunatech.demoapp.dtos.input.GeneroDtoInput;
import com.ao.musunatech.demoapp.dtos.output.GeneroDtoOutput;
import com.ao.musunatech.demoapp.dtos.output.LivroDtoOutput;
import com.ao.musunatech.demoapp.models.Genero;
import com.ao.musunatech.demoapp.repositories.GeneroRepository;
import com.ao.musunatech.demoapp.repositories.LivroRepository;
import com.ao.musunatech.demoapp.services.GeneroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GeneroImplementacao implements GeneroService {

    private GeneroRepository generoRepository;
    private LivroRepository livroRepository;

    public GeneroImplementacao(GeneroRepository generoRepository, LivroRepository livroRepository) {
        this.generoRepository = generoRepository;
        this.livroRepository = livroRepository;
    }

    /**
     * Cadastrar Gênero: Permitir a criação de novos gêneros literários (ex.: Ficção, Romance, Aventura).
     *
     * @param generoDtoInput
     * @return
     */
    @Override
    public GeneroDtoOutput cadastrar(GeneroDtoInput generoDtoInput) {
        Genero genero = new Genero();

        genero.setGeneroNome(generoDtoInput.generoNome());
        genero.setDescricao(generoDtoInput.descricao());

        var a = generoRepository.save(genero);

        GeneroDtoOutput generoDtoOutput = new GeneroDtoOutput(
                a.getId(),
                a.getGeneroNome(),
                a.getDescricao(),
                a.getCriadoEm(),
                a.getAtualizadoEm()
        );
        return generoDtoOutput;
    }

    /**Buscar Gênero por id: Permitir encontrar um gênero pelo id.
     *
     * @param id
     * @return
     */
    @Override
    public GeneroDtoOutput buscarPorId(Long id) {
        var a = generoRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado para o id: " + id));
        GeneroDtoOutput generoDtoOutput = new GeneroDtoOutput(
                a.getId(),
                a.getGeneroNome(),
                a.getDescricao(),
                a.getCriadoEm(),
                a.getAtualizadoEm()
        );
        return generoDtoOutput;
    }

    /**
     * Buscar Gênero por Nome: Permitir encontrar um gênero pelo nome.
     *
     * @param nome
     * @return
     */
    public GeneroDtoOutput buscarPorNome(String nome) {
        var a = generoRepository.findByName(nome)
                .orElseThrow(() -> new RuntimeException("Gênero não encontrado"));
        GeneroDtoOutput generoDtoOutput = new GeneroDtoOutput(
                a.getId(), a.getGeneroNome(), a.getDescricao(), a.getCriadoEm(), a.getAtualizadoEm()
        );
        return generoDtoOutput;
    }

    /**
     * Listar Gêneros: Retornar uma lista de todos os gêneros cadastrados.
     *
     * @return
     */
    @Override
    public List<GeneroDtoOutput> buscarTodos() {
        List<Genero> list = generoRepository.findAll();

        return list.stream()
                .map(value -> new GeneroDtoOutput(
                        value.getId(),
                        value.getGeneroNome(),
                        value.getDescricao(),
                        value.getCriadoEm(),
                        value.getAtualizadoEm()))
                .toList();
    }

    /**
     * Atualizar Gênero: Editar o nome ou descrição de um gênero.
     *
     * @param id
     * @param generoDtoInput
     * @return
     */
    @Override
    public GeneroDtoOutput atualizar(Long id, GeneroDtoInput generoDtoInput) {
        Genero genero = generoRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genero não encontrado com ID: " + id));

        genero.setGeneroNome(generoDtoInput.generoNome());
        genero.setDescricao(generoDtoInput.descricao());

        var a = generoRepository.save(genero);

        GeneroDtoOutput generoDtoOutput = new GeneroDtoOutput(
                a.getId(),
                a.getGeneroNome(),
                a.getDescricao(),
                a.getCriadoEm(),
                a.getAtualizadoEm()
        );
        return generoDtoOutput;
    }

    /**Deletar Gênero: Remover um gênero do sistema.
     *
     * @param id
     */
    @Override
    public void deletarPorId(Long id) {
        Optional<Genero> optionalGenero = generoRepository.findById(id);
        if (optionalGenero.isEmpty()) {
            throw new EntityNotFoundException("Gênero não encontrado para o id: " + id);
        }
        generoRepository.deleteById(id);
    }

    /**Buscar Livros de um Gênero: Listar todos os livros associados a um gênero específico.
     *
     * @param genero
     * @return
     */

    public List<LivroDtoOutput> buscarLivrosDeUmGenero(String genero) {
        return generoRepository.findByName(genero)
                .get().getLivros()
                .stream().map(value -> new LivroDtoOutput(
                        value.getId(),
                        value.getTitulo(),
                        value.getAnoDePublicacao(),
                        value.getIsbn(),
                        value.getNumeroDePagina(),
                        value.getIdioma(), value.getIdioma(), value.getCapa()))
                .collect(Collectors
                        .toList());
    }
}

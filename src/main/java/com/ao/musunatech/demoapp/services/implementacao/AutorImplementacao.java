package com.ao.musunatech.demoapp.services.implementacao;

import com.ao.musunatech.demoapp.dtos.input.AutorDtoInput;
import com.ao.musunatech.demoapp.dtos.output.AutorDtoOutput;
import com.ao.musunatech.demoapp.dtos.output.LivroDtoOutput;
import com.ao.musunatech.demoapp.models.Autor;
import com.ao.musunatech.demoapp.repositories.AutorRepository;
import com.ao.musunatech.demoapp.repositories.LivroRepository;
import com.ao.musunatech.demoapp.services.AutorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorImplementacao implements AutorService {

    private AutorRepository autorRepository;
    private LivroRepository livroRepository;

    public AutorImplementacao(AutorRepository autorRepository, LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
    }


    /**
     * Cadastrar Autor: Adicionar um novo autor ao sistema com informações como nome, data de nascimento, nacionalidade e biografia.
     *
     * @param autorDtoInput
     * @return
     */
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

    /**
     * Buscar Gênero por id: Permitir encontrar um gênero pelo id.
     *
     * @param id
     * @return
     */
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

    /**
     * Deletar Autor: Remover um autor do sistema.
     *
     * @param id
     */
    @Override
    public void deletarPorId(Long id) {
        autorRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Autor não encontrado para o ID: " + id));
        autorRepository.deleteById(id);
    }

    /**
     * Listar Autores: Recuperar uma lista de todos os autores cadastrados.
     *
     * @return
     */
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

    /**
     * Buscar Autor por Nome: Encontrar um autor pelo nome.
     *
     * @param nome
     * @return
     */
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

    /**
     * Atualizar Autor: Permitir alteração das informações de um autor existente.
     *
     * @param id
     * @param autorDtoInput
     * @return
     */
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

    /**
     * Buscar Livros de um Autor: Listar os livros associados a um autor específico.
     *
     * @param autor
     * @return
     */
    @Override
    public List<LivroDtoOutput> buscarLivrosDeUmAutor(String autor) {
        var a = autorRepository.findByAutorNome(autor)
                .orElseThrow(() ->
                        new EntityNotFoundException("Nenhum autor associado ao nome: " + autor));
        return a.getLivros().stream()
                .map(value -> new LivroDtoOutput(
                        value.getId(),
                        value.getTitulo(),
                        value.getAnoDePublicacao(),
                        value.getIsbn(),
                        value.getNumeroDePagina(),
                        value.getIdioma(),
                        value.getSinopse(),
                        value.getCapa()
                )).collect(Collectors
                        .toList());
    }

}

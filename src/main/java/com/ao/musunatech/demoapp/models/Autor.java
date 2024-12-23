package com.ao.musunatech.demoapp.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Autor {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long autorId;
    private String autorNome;
    private String nacionalidade;
    private LocalDateTime dataDeNascimento;
    private String biografia;

    @ManyToMany(mappedBy = "autores")
    private Set<Livro> livros = new HashSet<>();

    public Autor(){

    }

    public Autor(String autorNome,
                 String biografia,
                 Set<Livro> livros,
                 LocalDateTime dataDeNascimento,
                 String nacionalidade) {
        this.autorNome = autorNome;
        this.biografia = biografia;
        this.livros = livros;
        this.dataDeNascimento = dataDeNascimento;
        this.nacionalidade = nacionalidade;
    }

    public Long getId() {
        return autorId;
    }

    public void setId(Long id) {
        this.autorId = id;
    }

    public LocalDateTime getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDateTime dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getAutorNome() {
        return autorNome;
    }

    public void setAutorNome(String autorNome) {
        this.autorNome = autorNome;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public Set<Livro> getLivros() {
        return livros;
    }

    public void setLivros(Set<Livro> livros) {
        this.livros = livros;
    }
}

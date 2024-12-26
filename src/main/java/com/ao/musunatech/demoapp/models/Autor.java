package com.ao.musunatech.demoapp.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Autor {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String autorNome;
    private String nacionalidade;
    private LocalDate dataDeNascimento;
    private String biografia;

    @ManyToMany(mappedBy = "autores")
    private Set<Livro> livros = new HashSet<>();

    public Autor(){
        /* Construtor vazio*/
    }

    public Autor(String autorNome,
                 String biografia,
                 Set<Livro> livros,
                 LocalDate dataDeNascimento,
                 String nacionalidade) {
        this.autorNome = autorNome;
        this.biografia = biografia;
        this.livros = livros;
        this.dataDeNascimento = dataDeNascimento;
        this.nacionalidade = nacionalidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
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

    public Set<Livro> getLivros() {
        return livros;
    }

    public void setLivros(Set<Livro> livros) {
        this.livros = livros;
    }
}

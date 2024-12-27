package com.ao.musunatech.demoapp.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "GENERO",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "generoNome", "descricao"
                        }
                )
        }
)
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false, length = 55)
    private String generoNome;

    @Column(nullable = false, length = 512)
    private String descricao;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime criadoEm;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime atualizadoEm;

    @ManyToMany(mappedBy = "generos")
    private Set<Livro> livros = new HashSet<>();

    public Genero() {
        /* Construtor vazio */
    }

    public Genero(
            String generoNome,
            String descricao,
            LocalDateTime criadoEm,
            LocalDateTime atualizadoEm) {
        this.generoNome = generoNome;
        this.descricao = descricao;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
    }

    public Set<Livro> getLivros() {
        return livros;
    }

    public void setLivros(Set<Livro> livros) {
        this.livros = livros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getGeneroNome() {
        return generoNome;
    }

    public void setGeneroNome(String generoNome) {
        this.generoNome = generoNome;
    }

}

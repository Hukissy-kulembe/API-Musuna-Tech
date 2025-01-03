package com.ao.musunatech.demoapp.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "EDITORA",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "nifCnpj", "editoraNome"
                        }
                )
        }
)
public class Editora {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String editoraNome;
    @Column(nullable = false)
    private String nifCnpj;

    private String endereco;

    @OneToMany(mappedBy = "editora")
    private Set<Livro> livro = new HashSet<>();

    public Editora() {
        /* Construtor vazio */
    }

    public Editora(
                   String nifCnpj,
                   String endereco,
                   String editoraNome) {
        this.nifCnpj = nifCnpj;
        this.endereco = endereco;
        this.editoraNome = editoraNome;
    }

    public String getEditoraNome() {
        return editoraNome;
    }

    public void setEditoraNome(String editoraNome) {
        this.editoraNome = editoraNome;
    }

    public String getNifCnpj() {
        return nifCnpj;
    }

    public void setNifCnpj(String nifCnpj) {
        this.nifCnpj = nifCnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Livro> getLivro() {
        return livro;
    }

    public void setLivro(Set<Livro> livro) {
        this.livro = livro;
    }
}

package com.ao.musunatech.demoapp;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Editora {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long editoraId;
    private String editoraNome;
    private String nifCnpj;
    private String endereco;

    @OneToMany(mappedBy = "editora")
    private Set<Livro> livro = new HashSet<>();

    public Editora(String nifCnpj, String editoraNome, String endereco) {
        this.nifCnpj = nifCnpj;
        this.editoraNome = editoraNome;
        this.endereco = endereco;
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

    public Long getEditoraId() {
        return editoraId;
    }

    public void setEditoraId(Long editoraId) {
        this.editoraId = editoraId;
    }

    public Set<Livro> getLivro() {
        return livro;
    }

    public void setLivro(Set<Livro> livro) {
        this.livro = livro;
    }
}

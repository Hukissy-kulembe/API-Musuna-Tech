package com.ao.musunatech.demoapp;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Livro {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long livroId;
    private String titulo;
    private String isbn;
    private LocalDateTime anoDePublicacao;
    private List<String> genero;
    private int numeroDePagina;
    private String idioma;
    private String sinopse;
    private String capa;
    private String urlLivro;

     @ManyToOne
     @JoinColumn(name = "editoraId")
     private Editora editora;

     @ManyToMany
     @JoinTable(
             name = "Autor_Livro",
             joinColumns = @JoinColumn(name = "autorId"),
             inverseJoinColumns = @JoinColumn(name = "livroId")
     )
     private Set<Autor> autores = new HashSet<>();

    public Livro(){

    }

    public Livro(String urlLivro,
                 String capa,
                 String idioma,
                 String sinopse,
                 int numeroDePagina,
                 List<String> genero,
                 String isbn,
                 LocalDateTime anoDePublicacao,
                 String titulo) {
        this.urlLivro = urlLivro;
        this.capa = capa;
        this.idioma = idioma;
        this.sinopse = sinopse;
        this.numeroDePagina = numeroDePagina;
        this.genero = genero;
        this.isbn = isbn;
        this.anoDePublicacao = anoDePublicacao;
        this.titulo = titulo;
    }

    public Long getLivroId() {
        return livroId;
    }

    public void setLivroId(Long livroId) {
        this.livroId = livroId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getAnoDePublicacao() {
        return anoDePublicacao;
    }

    public void setAnoDePublicacao(LocalDateTime anoDePublicacao) {
        this.anoDePublicacao = anoDePublicacao;
    }

    public List<String> getGenero() {
        return genero;
    }

    public void setGenero(List<String> genero) {
        this.genero = genero;
    }

    public int getNumeroDePagina() {
        return numeroDePagina;
    }

    public void setNumeroDePagina(int numeroDePagina) {
        this.numeroDePagina = numeroDePagina;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public String getUrlLivro() {
        return urlLivro;
    }

    public void setUrlLivro(String urlLivro) {
        this.urlLivro = urlLivro;
    }

    public Set<Autor> getAutores() {
        return autores;
    }

    public void setAutores(Set<Autor> autores) {
        this.autores = autores;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "livroId=" + livroId +
                ", titulo='" + titulo + '\'' +
                ", isbn='" + isbn + '\'' +
                ", anoDePublicacao=" + anoDePublicacao +
                ", genero=" + genero +
                ", numeroDePagina=" + numeroDePagina +
                ", idioma='" + idioma + '\'' +
                ", sinopse='" + sinopse + '\'' +
                ", capa='" + capa + '\'' +
                ", urlLivro='" + urlLivro + '\'' +
                '}';
    }
}

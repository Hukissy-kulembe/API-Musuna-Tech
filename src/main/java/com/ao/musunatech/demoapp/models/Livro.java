package com.ao.musunatech.demoapp.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Livro {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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
             joinColumns = @JoinColumn(name = "livro_id"),
             inverseJoinColumns = @JoinColumn(name = "autor_id")
     )
     private Set<Autor> autores = new HashSet<>();

    public Livro(){

    }

    public Livro(Editora editora,
                 Set<Autor> autores,
                 String capa,
                 String urlLivro,
                 String sinopse,
                 String idioma,
                 List<String> genero,
                 int numeroDePagina,
                 LocalDateTime anoDePublicacao,
                 String isbn,
                 String titulo) {
        this.editora = editora;
        this.autores = autores;
        this.capa = capa;
        this.urlLivro = urlLivro;
        this.sinopse = sinopse;
        this.idioma = idioma;
        this.genero = genero;
        this.numeroDePagina = numeroDePagina;
        this.anoDePublicacao = anoDePublicacao;
        this.isbn = isbn;
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                "livroId=" + id +
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

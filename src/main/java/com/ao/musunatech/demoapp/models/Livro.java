package com.ao.musunatech.demoapp.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "LIVRO",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "capa"),
                @UniqueConstraint(columnNames = "urlLivro"),
                @UniqueConstraint(columnNames = "isbn")
        }
)
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(unique = true, nullable = false)
    private String isbn;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate anoDePublicacao;

    @Column(nullable = false)
    private int numeroDePagina;

    @Column(nullable = false)
    private String idioma;

    private String sinopse;

    @Column(unique = true ,nullable = false)
    private String capa;

    @Column(unique = true, nullable = false)
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "livro_genero",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "genero_id")
    )
    private Set<Genero> generos = new HashSet<>();

    public Livro() {
        /* Construtor vazio */
    }

    public Livro(Editora editora,
                 Set<Autor> autores,
                 Set<Genero> generos,
                 String capa,
                 String urlLivro,
                 String sinopse,
                 String idioma,
                 int numeroDePagina,
                 LocalDate anoDePublicacao,
                 String isbn,
                 String titulo) {
        this.editora = editora;
        this.autores = autores;
        this.generos = generos;
        this.capa = capa;
        this.urlLivro = urlLivro;
        this.sinopse = sinopse;
        this.idioma = idioma;
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

    public LocalDate getAnoDePublicacao() {
        return anoDePublicacao;
    }

    public void setAnoDePublicacao(LocalDate anoDePublicacao) {
        this.anoDePublicacao = anoDePublicacao;
    }

    public Set<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(Set<Genero> generos) {
        this.generos = generos;
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
                ", generos=" + generos +
                ", numeroDePagina=" + numeroDePagina +
                ", idioma='" + idioma + '\'' +
                ", sinopse='" + sinopse + '\'' +
                ", capa='" + capa + '\'' +
                ", urlLivro='" + urlLivro + '\'' +
                '}';
    }
}

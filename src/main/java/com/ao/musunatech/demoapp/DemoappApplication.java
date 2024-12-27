package com.ao.musunatech.demoapp;

import com.ao.musunatech.demoapp.models.Genero;
import com.ao.musunatech.demoapp.services.implementacao.AutorImplementacao;
import com.ao.musunatech.demoapp.services.implementacao.EditoraImplementacao;
import com.ao.musunatech.demoapp.services.implementacao.GeneroImplementacao;
import com.ao.musunatech.demoapp.services.implementacao.LivroImplementacao;
import com.ao.musunatech.demoapp.models.Autor;
import com.ao.musunatech.demoapp.models.Editora;
import com.ao.musunatech.demoapp.models.Livro;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DemoappApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoappApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(LivroImplementacao livroImplementacao,
                             AutorImplementacao autorImplementacao,
                             EditoraImplementacao editoraImplementacao,
                             GeneroImplementacao generoImplementacao) {
        return args -> {

            Genero genero1 = new Genero("Ficção Científica", "Explora futuros tecnológicos e descobertas científicas.", LocalDateTime.now(), LocalDateTime.now());
            Genero genero2 = new Genero("Romance", "Histórias focadas em relacionamentos amorosos.", LocalDateTime.now(), LocalDateTime.now());
            Genero genero3 = new Genero("Fantasia", "Histórias com elementos mágicos ou sobrenaturais.", LocalDateTime.now(), LocalDateTime.now());
            Genero genero4 = new Genero("Mistério", "Histórias envolvendo investigações ou enigmas.", LocalDateTime.now(), LocalDateTime.now());
            Genero genero5 = new Genero("Terror", "Histórias destinadas a assustar ou causar medo.", LocalDateTime.now(), LocalDateTime.now());
            Genero genero6 = new Genero("Drama", "Obras focadas em conflitos emocionais e dilemas profundos.", LocalDateTime.now(), LocalDateTime.now());

            Editora editora1 = new Editora(new HashSet<>(), "123456789", "Rua A, 123", "Editora Alpha");
            Editora editora2 = new Editora(new HashSet<>(), "987654321", "Avenida B, 456", "Editora Beta");

            // Instâncias de autores
            Autor autor1 = new Autor(
                    "Autor 1",
                    "Biografia do Autor 1",
                    new HashSet<>(),
                    LocalDate.of(1980, 6, 15),
                    "Brasileiro"
            );

            Autor autor2 = new Autor(
                    "Autor 2",
                    "Biografia do Autor 2",
                    new HashSet<>(),
                    LocalDate.of(1975, 4, 10),
                    "Argentino"
            );

            Autor autor3 = new Autor(
                    "Autor 3",
                    "Biografia do Autor 3",
                    new HashSet<>(),
                    LocalDate.of(1990, 11, 20),
                    "Português"
            );


            Livro livro1 = new Livro(
                    editora1,
                    Set.of(autor1, autor2),
                    Set.of(genero5),
                    "capa1.jpg",
                    "https://url-livro1.com",
                    "Sinopse do livro 1.",
                    "Português",
                    320,
                    LocalDate.of(2020, 5, 15),
                    "978-3-16-148410-0",
                    "Título do Livro 1"
            );

            Livro livro2 = new Livro(
                    editora2,
                    Set.of(autor1, autor2, autor3),
                    Set.of(genero4),
                    "capa2.jpg",
                    "https://url-livro2.com",
                    "Sinopse do livro 2.",
                    "Inglês",
                    250,
                    LocalDate.of(2019, 8, 20),
                    "978-1-23-456789-7",
                    "Título do Livro 2"
            );

            Livro livro3 = new Livro(
                    editora1,
                    Set.of(autor1),
                    Set.of(genero3),
                    "capa3.png",
                    "https://url-livro3.com",
                    "Sinopse do livro 3.",
                    "Espanhol",
                    400,
                    LocalDate.of(2021, 3, 10),
                    "978-0-12-345678-9",
                    "Título do Livro 3"
            );

            Livro livro4 = new Livro(
                    editora1,
                    Set.of(autor1, autor3),
                    Set.of(genero2, genero4, genero5, genero1),
                    "capa4.jpg",
                    "https://url-livro4.com",
                    "Sinopse do livro 4.",
                    "Francês",
                    180,
                    LocalDate.of(2015, 7, 25),
                    "978-3-14-159265-4",
                    "Título do Livro 4"
            );

            Livro livro5 = new Livro(
                    editora2,
                    Set.of(autor1, autor2),
                    Set.of(genero1),
                    "capa5.png",
                    "https://url-livro5.com",
                    "Sinopse do livro 5.",
                    "Português",
                    500,
                    LocalDate.of(2018, 1, 1),
                    "978-0-11-122334-5",
                    "Título do Livro 5"
            );

            editoraImplementacao.cadastrarEditora(editora1);
            editoraImplementacao.cadastrarEditora(editora2);

            generoImplementacao.cadastrarGenero(genero1);
            generoImplementacao.cadastrarGenero(genero2);
            generoImplementacao.cadastrarGenero(genero3);
            generoImplementacao.cadastrarGenero(genero4);
            generoImplementacao.cadastrarGenero(genero5);
            generoImplementacao.cadastrarGenero(genero6);

            autorImplementacao.cadastrarAutor(autor1);
            autorImplementacao.cadastrarAutor(autor2);
            autorImplementacao.cadastrarAutor(autor3);

            livroImplementacao.cadastrarLivro(livro1);
            livroImplementacao.cadastrarLivro(livro2);
            livroImplementacao.cadastrarLivro(livro3);
            livroImplementacao.cadastrarLivro(livro4);
            livroImplementacao.cadastrarLivro(livro5);

            var a = livroImplementacao.buscarPorId(2l);
            System.out.println(a);

            var ba = autorImplementacao.buscarPorId(autor2.getId());
            System.out.println(ba);

            //livroImplementacao.deletarPorId(a.id());
        };
    }
}

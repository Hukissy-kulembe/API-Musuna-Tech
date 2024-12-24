package com.ao.musunatech.demoapp;

import com.ao.musunatech.demoapp.implementacao.AutorImplementacao;
import com.ao.musunatech.demoapp.implementacao.EditoraImplementacao;
import com.ao.musunatech.demoapp.implementacao.LivroImplementacao;
import com.ao.musunatech.demoapp.models.Autor;
import com.ao.musunatech.demoapp.models.Editora;
import com.ao.musunatech.demoapp.models.Livro;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class DemoappApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoappApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(LivroImplementacao livroImplementacao,
                             AutorImplementacao autorImplementacao,
                             EditoraImplementacao editoraImplementacao) {
        return args -> {

            Editora editora1 = new Editora(new HashSet<>(), "123456789", "Rua A, 123", "Editora Alpha");
            Editora editora2 = new Editora(new HashSet<>(), "987654321", "Avenida B, 456", "Editora Beta");

            // Instâncias de autores
            Autor autor1 = new Autor(
                    "Autor 1",
                    "Biografia do Autor 1",
                    new HashSet<>(),
                    LocalDateTime.of(1980, 6, 15, 0, 0),
                    "Brasileiro"
            );

            Autor autor2 = new Autor(
                    "Autor 2",
                    "Biografia do Autor 2",
                    new HashSet<>(),
                    LocalDateTime.of(1975, 4, 10, 0, 0),
                    "Argentino"
            );

            Autor autor3 = new Autor(
                    "Autor 3",
                    "Biografia do Autor 3",
                    new HashSet<>(),
                    LocalDateTime.of(1990, 11, 20, 0, 0),
                    "Português"
            );


            Livro livro1 = new Livro(
                    editora1,
                    Set.of(autor1, autor2),
                    "capa1.jpg",
                    "https://url-livro1.com",
                    "Sinopse do livro 1.",
                    "Português",
                    List.of("Ficção", "Aventura"),
                    320,
                    LocalDateTime.of(2020, 5, 15, 0, 0),
                    "978-3-16-148410-0",
                    "Título do Livro 1"
            );

            Livro livro2 = new Livro(
                    editora2,
                    Set.of(autor1, autor2, autor3),
                    "capa2.jpg",
                    "https://url-livro2.com",
                    "Sinopse do livro 2.",
                    "Inglês",
                    List.of("Romance", "Drama"),
                    250,
                    LocalDateTime.of(2019, 8, 20, 0, 0),
                    "978-1-23-456789-7",
                    "Título do Livro 2"
            );

            Livro livro3 = new Livro(
                    editora1,
                    Set.of(autor1),
                    "capa3.png",
                    "https://url-livro3.com",
                    "Sinopse do livro 3.",
                    "Espanhol",
                    List.of("Suspense", "Mistério"),
                    400,
                    LocalDateTime.of(2021, 3, 10, 0, 0),
                    "978-0-12-345678-9",
                    "Título do Livro 3"
            );

            Livro livro4 = new Livro(
                    editora1,
                    Set.of(autor1, autor3),
                    "capa4.jpg",
                    "https://url-livro4.com",
                    "Sinopse do livro 4.",
                    "Francês",
                    List.of("Biografia"),
                    180,
                    LocalDateTime.of(2015, 7, 25, 0, 0),
                    "978-3-14-159265-4",
                    "Título do Livro 4"
            );

            Livro livro5 = new Livro(
                    editora2,
                    Set.of(autor1, autor2),
                    "capa5.png",
                    "https://url-livro5.com",
                    "Sinopse do livro 5.",
                    "Português",
                    List.of("Ciência", "Tecnologia"),
                    500,
                    LocalDateTime.of(2018, 1, 1, 0, 0),
                    "978-0-11-122334-5",
                    "Título do Livro 5"
            );

            editoraImplementacao.cadastrarEditora(editora1);
            editoraImplementacao.cadastrarEditora(editora2);


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

            livroImplementacao.deletarPorId(a.getId());
        };
    }
}

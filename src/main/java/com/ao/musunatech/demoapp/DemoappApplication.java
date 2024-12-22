package com.ao.musunatech.demoapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@SpringBootApplication
public class DemoappApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoappApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(LivroRepository livroRepository) {
        return args -> {
            Livro livro1 = new Livro("https://example.com/livro1", "capa1.jpg", "Português", "Sinopse do livro 1", 200, Arrays.asList("Ficção", "Aventura"), "978-3-16-148410-0", LocalDateTime.of(2020, 5, 15, 0, 0), "Livro 1");
            Livro livro2 = new Livro("https://example.com/livro2", "capa2.jpg", "Inglês", "Sinopse do livro 2", 150, Arrays.asList("Romance", "Drama"), "978-3-16-148410-1", LocalDateTime.of(2021, 6, 20, 0, 0), "Livro 2");
            Livro livro3 = new Livro("https://example.com/livro3", "capa3.jpg", "Espanhol", "Sinopse do livro 3", 300, Arrays.asList("Terror", "Suspense"), "978-3-16-148410-2", LocalDateTime.of(2019, 3, 10, 0, 0), "Livro 3");
            Livro livro4 = new Livro("https://example.com/livro4", "capa4.jpg", "Francês", "Sinopse do livro 4", 400, Arrays.asList("Fantasia"), "978-3-16-148410-3", LocalDateTime.of(2022, 8, 25, 0, 0), "Livro 4");
            Livro livro5 = new Livro("https://example.com/livro5", "capa5.jpg", "Português", "Sinopse do livro 5", 180, Arrays.asList("Biografia"), "978-3-16-148410-4", LocalDateTime.of(2018, 12, 5, 0, 0), "Livro 5");
            Livro livro6 = new Livro("https://example.com/livro6", "capa6.jpg", "Inglês", "Sinopse do livro 6", 250, Arrays.asList("História"), "978-3-16-148410-5", LocalDateTime.of(2017, 7, 30, 0, 0), "Livro 6");
            Livro livro7 = new Livro("https://example.com/livro7", "capa7.jpg", "Alemão", "Sinopse do livro 7", 320, Arrays.asList("Ciência"), "978-3-16-148410-6", LocalDateTime.of(2016, 11, 11, 0, 0), "Livro 7");
            Livro livro8 = new Livro("https://example.com/livro8", "capa8.jpg", "Italiano", "Sinopse do livro 8", 275, Arrays.asList("Arte"), "978-3-16-148410-7", LocalDateTime.of(2015, 1, 20, 0, 0), "Livro 8");
            Livro livro9 = new Livro("https://example.com/livro9", "capa9.jpg", "Português", "Sinopse do livro 9", 230, Arrays.asList("Poesia"), "978-3-16-148410-8", LocalDateTime.of(2023, 2, 14, 0, 0), "Livro 9");
            Livro livro10 = new Livro("https://example.com/livro10", "capa10.jpg", "Chinês", "Sinopse do livro 10", 310, Arrays.asList("Negócios"), "978-3-16-148410-9", LocalDateTime.of(2021, 9, 17, 0, 0), "Livro 10");

            livroRepository.save(livro1);
            livroRepository.save(livro2);
            livroRepository.save(livro3);
            livroRepository.save(livro4);
            livroRepository.save(livro5);
            livroRepository.save(livro6);
            livroRepository.save(livro7);
            livroRepository.save(livro8);
            livroRepository.save(livro9);
            livroRepository.save(livro10);

            Livro livro = livroRepository
                    .findById(livro1.getLivroId())
                    .orElseThrow(NoSuchElementException::new);

            System.out.println(livro.toString());

        };
    }
}

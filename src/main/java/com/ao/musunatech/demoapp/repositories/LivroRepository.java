package com.ao.musunatech.demoapp.repositories;

import com.ao.musunatech.demoapp.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT l FROM Livro l WHERE l.isbn = :isbn")
    Optional<Livro> findByIsbn(@Param("isbn") String isbn);

    @Query("SELECT l FROM Livro l WHERE l.titulo = :titulo")
    Optional<Livro> findByTitulo(@Param("titulo") String titulo);


}

package com.ao.musunatech.demoapp.repositories;

import com.ao.musunatech.demoapp.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE a.autorNome = :autorNome")
    Optional<Autor> findByAutorNome(@Param("autorNome") String autorNome);

}

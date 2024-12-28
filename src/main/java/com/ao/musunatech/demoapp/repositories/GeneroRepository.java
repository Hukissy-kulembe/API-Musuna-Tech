package com.ao.musunatech.demoapp.repositories;

import com.ao.musunatech.demoapp.models.Genero;
import com.ao.musunatech.demoapp.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GeneroRepository extends JpaRepository<Genero, Long> {

    @Query("SELECT g FROM Genero g WHERE g.generoNome = :generoNome")
    Optional<Genero> findByName(@Param("generoNome") String nome);

}

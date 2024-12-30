package com.ao.musunatech.demoapp.repositories;

import com.ao.musunatech.demoapp.models.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EditoraRepository extends JpaRepository<Editora, Long> {

    @Query("SELECT e FROM Editora e WHERE e.editoraNome = :editoraNome")
    Optional<Editora> findByName(@Param("editoraNome") String editoraNome);

}

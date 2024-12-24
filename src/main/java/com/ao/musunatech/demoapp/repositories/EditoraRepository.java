package com.ao.musunatech.demoapp.repositories;

import com.ao.musunatech.demoapp.models.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EditoraRepository extends JpaRepository<Editora, Long> {

}

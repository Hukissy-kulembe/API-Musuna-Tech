package com.ao.musunatech.demoapp.repositories;

import com.ao.musunatech.demoapp.models.Editora;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EditoraRepository extends CrudRepository<Editora, Long> {

    Editora save(Editora editora);

    @Override
    Optional<Editora> findById(Long aLong);
}

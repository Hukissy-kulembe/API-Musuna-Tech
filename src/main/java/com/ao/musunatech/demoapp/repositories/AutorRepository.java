package com.ao.musunatech.demoapp.repositories;

import com.ao.musunatech.demoapp.models.Autor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AutorRepository extends CrudRepository<Autor, Long> {

    Autor save(Autor autor);

    @Override
    Optional<Autor> findById(Long id);

    @Override
    void deleteById(Long aLong);

}

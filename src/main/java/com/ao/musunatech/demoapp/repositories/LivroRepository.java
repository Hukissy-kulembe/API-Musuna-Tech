package com.ao.musunatech.demoapp.repositories;

import com.ao.musunatech.demoapp.models.Livro;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface LivroRepository extends Repository<Livro, Long> {

    Livro save(Livro livro);
    Optional<Livro> findById(Long id);

}

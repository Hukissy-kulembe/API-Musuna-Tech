package com.ao.musunatech.demoapp.repositories;

import com.ao.musunatech.demoapp.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}

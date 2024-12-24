package com.ao.musunatech.demoapp.services;

import com.ao.musunatech.demoapp.models.Livro;

public interface LivroService {

    Livro cadastrarLivro(Livro livro);
    Livro buscarPorId(Long id);
    void deletarPorId(Long id);

}

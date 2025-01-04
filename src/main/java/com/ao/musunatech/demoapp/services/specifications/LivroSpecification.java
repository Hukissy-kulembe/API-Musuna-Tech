package com.ao.musunatech.demoapp.services.specifications;

import com.ao.musunatech.demoapp.models.Livro;
import com.ao.musunatech.demoapp.repositories.LivroRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.util.stream.Collectors;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

public class LivroSpecification {

    private static final LivroRepository livroRepository = null;


    public static Specification<Livro> livroDtoOutputSpecification (String titulo) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(titulo))
                return null;

            return builder.like(root.get("titulo"), "%" + titulo + "%");
        };
    }

    public static Specification<Livro> autorLivroEquals(String autor) {

        /*var autors = livroRepository
               .findAll()
               .stream()
               .map(value -> value.getAutores()
                       .stream()
                       .filter(name -> name.getAutorNome()
                               .equals(autor))).collect(Collectors.toSet());

         */

       return (root, query, builder) -> {
           if (ObjectUtils.isEmpty(autor))
               return null;

           return builder.like(root.get("editora"), "%"+ autor+"%");
       };

    }

}

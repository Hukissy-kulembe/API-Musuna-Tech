package com.ao.musunatech.demoapp.dtos.input;

import java.io.Serializable;

public record EditoraDtoInput(
        Long id,
        String nome,
        String nifCnpj,
        String endereco
) implements Serializable {
}

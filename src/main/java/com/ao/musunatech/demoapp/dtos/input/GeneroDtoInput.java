package com.ao.musunatech.demoapp.dtos.input;

import java.io.Serializable;
import java.time.LocalDateTime;

public record GeneroDtoInput(
        String generoNome,
        String descricao,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) implements Serializable {
}

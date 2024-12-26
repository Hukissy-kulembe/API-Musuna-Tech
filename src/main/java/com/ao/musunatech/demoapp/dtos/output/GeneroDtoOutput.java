package com.ao.musunatech.demoapp.dtos.output;

import java.io.Serializable;
import java.time.LocalDateTime;

public record GeneroDtoOutput(
        Long id,
        String generoNome,
        String descricao,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) implements Serializable {
}

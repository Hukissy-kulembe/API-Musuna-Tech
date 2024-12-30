package com.ao.musunatech.demoapp.dtos.output;

import java.io.Serializable;

public record EditoraDtoOutput(
        Long id,
        String nifCjpj,
        String endereco,
        String editoraNome
) implements Serializable {
}

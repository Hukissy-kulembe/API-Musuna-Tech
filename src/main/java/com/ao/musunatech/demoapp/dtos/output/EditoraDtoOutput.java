package com.ao.musunatech.demoapp.dtos.output;

import java.io.Serializable;

public record EditoraDtoOutput(
        String nifCjpj,
        String endereco,
        String editoraNome
) implements Serializable {
}

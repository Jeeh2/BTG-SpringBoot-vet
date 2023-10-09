package com.vet.api.controller.DTO;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaVet(
        @NotNull
        Integer id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}

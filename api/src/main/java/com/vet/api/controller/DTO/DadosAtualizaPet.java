package com.vet.api.controller.DTO;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaPet(@NotNull
                               Integer id,
                               String nome,
                               String telefone,
                               DadosEndereco endereco) {
}

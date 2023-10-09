package com.vet.api.controller.DTO;

import com.vet.api.veterinario.Veterinario;

public record DadosListagemVeterinario(Integer id, String nome, String email, String crm) {

    public DadosListagemVeterinario(Veterinario veterinario){
        this(veterinario.getId(), veterinario.getNome(), veterinario.getEmail(), veterinario.getCrm());
    }
}

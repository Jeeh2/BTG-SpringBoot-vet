package com.vet.api.veterinario;

import com.vet.api.endereco.Endereco;

public record DadosTelhamentoVeterinario(Integer id, String nome, String email, String telefone, String crm, Endereco endereco) {

    public DadosTelhamentoVeterinario(Veterinario veterinario){
        this(veterinario.getId(), veterinario.getNome(),
                veterinario.getEmail(), veterinario.getTelefone(),
                veterinario.getCrm(), veterinario.getEndereco());
    }
}

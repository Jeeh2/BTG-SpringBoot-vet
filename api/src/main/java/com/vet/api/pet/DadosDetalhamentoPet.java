package com.vet.api.pet;

import com.vet.api.endereco.Endereco;

public record DadosDetalhamentoPet(String nome, String email, String telefone, String cpf, Endereco endereco) {
    public DadosDetalhamentoPet(Pet pet) {
        this(pet.getNome(), pet.getEmail(), pet.getTelefone(), pet.getCpf(), pet.getEndereco());
    }
}

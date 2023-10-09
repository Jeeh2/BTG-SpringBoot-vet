package com.vet.api.controller.DTO;

import com.vet.api.pet.Pet;
import com.vet.api.veterinario.Veterinario;

public record DadosListagemPets(String nome, String telefone, String email, String cpf) {

    public DadosListagemPets(Pet pet){
        this(pet.getNome(),pet.getTelefone(), pet.getEmail(), pet.getCpf());
    }
}

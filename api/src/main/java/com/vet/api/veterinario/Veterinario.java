package com.vet.api.veterinario;

import com.vet.api.controller.DTO.DadosAtualizaVet;
import com.vet.api.controller.DTO.DadosCadastroVet;
import com.vet.api.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "veterinarios")
@Entity(name = "veterinario")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Veterinario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;


    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Veterinario(DadosCadastroVet dados){
        this.ativo = true;
        this.nome = dados.nome();
        this.crm = dados.crm();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizaVet dados){

        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}

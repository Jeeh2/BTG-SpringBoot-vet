package com.vet.api.controller;

import com.vet.api.controller.DTO.*;
import com.vet.api.pet.DadosDetalhamentoPet;
import com.vet.api.pet.Pet;
import com.vet.api.repository.IPetRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pets")
public class PetController {

    @Autowired
    private IPetRepository iPetRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPet dados, UriComponentsBuilder uriBuilder) {
        var pet = new Pet(dados);

        iPetRepository.save(pet);

        var uri = uriBuilder.path("/pets/{id}").buildAndExpand(pet.getId()).toUri();


    }


    @GetMapping
    public ResponseEntity<Page<DadosListagemPets>> listarPets(@PageableDefault(page = 0, size = 5, sort = { "nome" }) Pageable paginacao) {
        var page = iPetRepository.findAllByAtivoTrue(paginacao).map(DadosListagemPets::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizaPet dados) {

        var pet = iPetRepository.getReferenceById(dados.id());
        pet.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoPet(pet));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Integer id) {
        var pet = iPetRepository.getReferenceById(id);
        pet.inativar();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Integer id) {
        var pet = iPetRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPet(pet));
    }
}


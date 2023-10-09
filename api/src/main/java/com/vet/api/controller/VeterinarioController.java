package com.vet.api.controller;

import com.vet.api.controller.DTO.DadosAtualizaVet;
import com.vet.api.controller.DTO.DadosListagemVeterinario;
import com.vet.api.repository.IVeterinarioRepository;
import com.vet.api.controller.DTO.DadosCadastroVet;
import com.vet.api.veterinario.DadosTelhamentoVeterinario;
import com.vet.api.veterinario.Veterinario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController //anotação usada para api rest
@RequestMapping("/veterinarios") //qual url que esse controller vai responder
public class VeterinarioController {

    @Autowired
    private IVeterinarioRepository iVeterinarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroVet dados, UriComponentsBuilder uriComponentsBuilder){
        var veterinario = new Veterinario(dados);

        iVeterinarioRepository.save(veterinario);

        var uri = uriComponentsBuilder.path("/veterinarios/{id}").buildAndExpand(veterinario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosTelhamentoVeterinario(veterinario));
    }

    @GetMapping
    public ResponseEntity< Page<DadosListagemVeterinario>> listar(@PageableDefault(size= 5, sort = {"nome"}) Pageable paginacao){
        var page = iVeterinarioRepository.findAllByAtivoTrue(paginacao).map(DadosListagemVeterinario::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizaVet dados){

        var veterinario = iVeterinarioRepository.getReferenceById(dados.id());
        veterinario.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosTelhamentoVeterinario(veterinario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Integer id){
        var veterinario = iVeterinarioRepository.getReferenceById(id);
        veterinario.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Integer id){
        var veterinario = iVeterinarioRepository.getReferenceById(id);


        return ResponseEntity.ok(new DadosTelhamentoVeterinario(veterinario));
    }


}

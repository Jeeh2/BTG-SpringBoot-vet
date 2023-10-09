package com.vet.api.controller;

import com.vet.api.controller.DTO.DadosAutenticacao;
import com.vet.api.infra.security.DadosToken;
import com.vet.api.service.TokenService;
import com.vet.api.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
       var authentication =  manager.authenticate(token);
       var authenticationToken = tokenService.token((Usuario) authentication.getPrincipal());

       return ResponseEntity.ok(new DadosToken(authenticationToken));

    }
}

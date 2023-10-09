package com.vet.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class )
    public ResponseEntity tratarError404(){
      return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class )
    public ResponseEntity tratarError400(MethodArgumentNotValidException ex){

        var erros = ex.getFieldErrors();
      return ResponseEntity.badRequest().body(erros.stream().map(DadosValidacao ::new).toList());
    }

     private record DadosValidacao(String campo, String mensagem){
        public DadosValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }

     }
}

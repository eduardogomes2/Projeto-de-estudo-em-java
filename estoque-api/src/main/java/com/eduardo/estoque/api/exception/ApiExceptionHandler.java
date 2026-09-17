package com.eduardo.estoque.api.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> tratarNaoEncontrado(
            EntityNotFoundException exception) {

        Map<String, String> resposta = Map.of(
                "erro", exception.getMessage()
        );

        return ResponseEntity.status(404).body(resposta);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> tratarValidacao(
            MethodArgumentNotValidException exception) {

        Map<String, String> erros = new LinkedHashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(erro ->
                        erros.put(erro.getField(), erro.getDefaultMessage()));

        return ResponseEntity.badRequest().body(erros);
    }
}
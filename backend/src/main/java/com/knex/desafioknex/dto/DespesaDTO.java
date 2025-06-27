package com.knex.desafioknex.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public record DespesaDTO (
    Long id,

    @NotBlank (message = "Esse campo e obrigatorio")
    LocalDateTime dataEmissao,

    @NotBlank (message = "Esse campo e obrigatorio")
    String fornecedor,

    @NotBlank (message = "Esse campo e obrigatorio")
    Float valorLiquido,

    @NotBlank (message = "Esse campo e obrigatorio")
    String url

    
){}

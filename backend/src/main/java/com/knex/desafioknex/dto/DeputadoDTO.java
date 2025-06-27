package com.knex.desafioknex.dto;

import jakarta.validation.constraints.NotBlank;

public record DeputadoDTO(

    Long id, 

    @NotBlank (message = "Esse campo e obrigatorio")
    String nome,

    @NotBlank (message = "Esse campo e obrigatorio")
    String uf,

    
    @NotBlank (message = "Esse campo e obrigatorio")
    String cpf,

    
    @NotBlank (message = "Esse campo e obrigatorio")
    String partido


    

){}

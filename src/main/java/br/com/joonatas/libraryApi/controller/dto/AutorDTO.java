package br.com.joonatas.libraryApi.controller.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

//Representação do contrato da API.

//Utilizando Mapper.

public record AutorDTO(UUID id,
                       @NotBlank(message = "Campo obrigatório!")
                       @Size(min = 2, max = 100, message = "Campo fora do tamanho padrão")
                       String nome,
                       @NotNull(message = "Campo obrigatório")
                       @Past(message = "Não pode ser uma data futura.")
                       LocalDate dataNascimento,
                       @NotBlank(message = "Campo obrigatório")
                       @Size(min = 2,max = 50, message = "Campo fora do tamanho padrão")
                       String nacionalidade) {
}

package br.com.joonatas.libraryApi.controller.dto;

import br.com.joonatas.libraryApi.model.GeneroLivro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;
import java.time.LocalDate;

//Utilizando Mapper.

public record cadastroLivroDTO(
        @ISBN
        @NotBlank(message = "")
        String isbn,
        @NotBlank(message = "")
        String titulo,
        @NotNull(message = "")
        @Past(message = "")
        LocalDate dataLancamento,
        GeneroLivro genero,
        BigDecimal preco,
        @NotBlank(message = "")
        UUID idAutor) {
    }

package br.com.joonatas.libraryApi.controller.dto;

import br.com.joonatas.libraryApi.model.GeneroLivro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ResultadoPesquisaLivroDTO(UUID id,
                                        String isbn,
                                        String tituilo,
                                        LocalDate dataLancamento,
                                        GeneroLivro genero,
                                        BigDecimal preco,
                                        AutorDTO autorDTO) {
}

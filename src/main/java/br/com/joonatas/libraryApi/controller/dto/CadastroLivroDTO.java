package br.com.joonatas.libraryApi.controller.dto;

import br.com.joonatas.libraryApi.model.GeneroLivro;
import br.com.joonatas.libraryApi.model.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CadastroLivroDTO(String isbn,
                               String tituilo,
                               LocalDate dataLancamento,
                               GeneroLivro genero,
                               BigDecimal preco,
                               UUID idAutor) {
    public Livro mapearLivro(){
        Livro livro = new Livro();

        livro.setIsbn(isbn);
        livro.setTitulo(tituilo);
        livro.setDataLancamento(dataLancamento);
        livro.setGenero(genero);
        livro.setPreco(preco);
        livro.setIdusuario(idAutor);
        return livro;
    }
}

package br.com.joonatas.libraryApi.controller.dto;


import br.com.joonatas.libraryApi.model.Autor;

import java.time.LocalDate;
import java.util.UUID;

//Representação do contrato da API.

public record AutorDTO(UUID id,
                       String nome,
                       LocalDate dataNascimento,
                       String nacionalidade) {

    public Autor mapearParaAutor(){
        Autor autor = new Autor();

        autor.setNome(this.nome);
        autor.setDataNasc(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}

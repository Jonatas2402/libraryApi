package br.com.joonatas.libraryApi.controller.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErroResposta(int status,
                           String mensagem,
                           List<ErroCampo> erros) {
    //Cria uma mensagem de erro padrão
    public static ErroResposta respostaPadrao(String mensagem){
        return new ErroResposta(HttpStatus.BAD_REQUEST.value(), mensagem, List.of());
    }

    //cria uma mensagem de conflito
    public static ErroResposta conflito(String mensagem){
        return new ErroResposta(HttpStatus.CONFLICT.value(), mensagem, List.of());
    }
}

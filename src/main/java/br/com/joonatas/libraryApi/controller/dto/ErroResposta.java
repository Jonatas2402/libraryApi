package br.com.joonatas.libraryApi.controller.dto;

import java.util.List;

public record ErroResposta(int status,
                           String mensagem,
                           List<ErroCampo> erros) {
}

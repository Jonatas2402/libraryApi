package br.com.joonatas.libraryApi.controller;

import br.com.joonatas.libraryApi.controller.dto.ErroResposta;
import br.com.joonatas.libraryApi.controller.dto.cadastroLivroDTO;
import br.com.joonatas.libraryApi.exceptions.RegistroDuplicadoExceptions;
import br.com.joonatas.libraryApi.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService service;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody cadastroLivroDTO dto){
        try {
            return ResponseEntity.ok(dto);
        }catch (RegistroDuplicadoExceptions e){
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }


}

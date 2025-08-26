package br.com.joonatas.libraryApi.controller;

import br.com.joonatas.libraryApi.controller.dto.CadastroLivroDTO;
import br.com.joonatas.libraryApi.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService service;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody CadastroLivroDTO livroDTO){
        var livroEntidade = livroDTO.mapearLivro();
        service.salvar(livroEntidade);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id")
                .buildAndExpand(livroEntidade)
                .toUri();

        return ResponseEntity.created(location).build();
    }


}

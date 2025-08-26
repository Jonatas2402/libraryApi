package br.com.joonatas.libraryApi.controller;

import br.com.joonatas.libraryApi.controller.dto.AutorDTO;
import br.com.joonatas.libraryApi.controller.dto.ErroResposta;
import br.com.joonatas.libraryApi.exceptions.OperacaoNaoPermitidaException;
import br.com.joonatas.libraryApi.exceptions.RegistroDuplicadoExceptions;
import br.com.joonatas.libraryApi.model.Autor;
import br.com.joonatas.libraryApi.service.AutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService service;


    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid AutorDTO autor) {
        try {
            var autorEntidade = autor.mapearParaAutor();
            service.salvar(autorEntidade);

            //http://localhost:8080/autores/1561643546843541
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()//Pega a requisição atual.
                    .path("/{id}")//Acrescenta o id no final.
                    .buildAndExpand(autorEntidade.getId())//Substitui {id} pelo id real do usuário.
                    .toUri();//Constrói uma URI final.

            return ResponseEntity
                    .created(location) //Retorna status "201 ok" e define url no cabeçalho.
                    .build(); //Retorna o usuário criado no corpo da resposta.
        } catch (RegistroDuplicadoExceptions e) {
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<AutorDTO> obterDetelhes(@PathVariable("id") String id){
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);
        if (autorOptional.isPresent()){
            Autor autor = autorOptional.get();
            AutorDTO dto = new AutorDTO(
                    autor.getId(),
                    autor.getNome(),
                    autor.getDataNasc(),
                    autor.getNacionalidade());
            return ResponseEntity.ok(dto);
            /*Caso exista um autor essa será a resposta*/
        }
        return ResponseEntity.notFound().build();
        /*Caso não exista um autor essa será a resposta.*/
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletaAutor(@PathVariable("id") String id){
        try {
            var idAutor = UUID.fromString(id);
            Optional<Autor> autorOptional = service.obterPorId(idAutor);
            if (autorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();//Se não houver autor com esse id.
            }
            service.deletar(autorOptional.get());
            return ResponseEntity.noContent().build();
        } catch (OperacaoNaoPermitidaException e){
            var erro = ErroResposta.respostaPadrao(e.getMessage());
            return ResponseEntity.status(erro.status()).body(erro);
        }
    }
    @GetMapping
    public ResponseEntity<List<AutorDTO>> pesquisar(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "nacionalidade", required = false) String nacionalidade){
        List<Autor> resultado = service.pesquisaByExample(nome,nacionalidade);
        //Transformando a lista em autorDTO.
        List<AutorDTO> lista = resultado
                .stream()
                .map(autor -> new AutorDTO(
                        autor.getId(),
                        autor.getNome(),
                        autor.getDataNasc(),
                        autor.getNacionalidade())
                ).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }
    @PutMapping("{id}")
    public ResponseEntity<Object> atualizaAutor(@PathVariable("id") String id, @RequestBody @Valid AutorDTO dto){
        //Trsnformando o id em UUID
        try {
            var idAutor = UUID.fromString(id);
            Optional<Autor> autorOptional = service.obterPorId(idAutor);
            if (autorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();//Se não houver autor com esse id.
            } //Quando não houver autor será lançada a mensagem de erro pelo ResponseEntity.
            var autor = autorOptional.get();
            autor.setNome(dto.nome());
            autor.setDataNasc(dto.dataNascimento());
            autor.setNacionalidade(dto.nacionalidade());
            service.atualizar(autor);
            return ResponseEntity.noContent().build();
        }catch (RegistroDuplicadoExceptions e){
            var erroDTO = ErroResposta.respostaPadrao(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }

}

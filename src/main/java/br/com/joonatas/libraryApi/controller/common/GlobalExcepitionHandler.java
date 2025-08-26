package br.com.joonatas.libraryApi.controller.common;

import br.com.joonatas.libraryApi.controller.dto.ErroCampo;
import br.com.joonatas.libraryApi.controller.dto.ErroResposta;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.validation.ValidatorHandler;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice //Captura exceptions e da uma resposta rest
public class GlobalExcepitionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) //Retorna uma resposta de erro sempre.
    public ErroResposta hamdleMethodArgomentNotValidExcepition(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErroCampo> listaDeErros = fieldErrors.
                stream().
                map(fe -> new ErroCampo(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ErroResposta(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação", listaDeErros);
    }
}

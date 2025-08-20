package br.com.joonatas.libraryApi.repository;

import br.com.joonatas.libraryApi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransacoesTest {

    @Autowired
    TransacaoService transacaoService;
    /*
    * commit -> confirmar as alterações
    * rollback -> desfazer as alterações
     */

    @Test
    //Uma transação resolve todos esses problemas.
    void transacoesSimples(){
        transacaoService.executar();
        //salvar um livro
        //salvar o autor
        //alugar um livro
        //enviar email para locatário
        //notificar que o livro saiu da livraria
    }
}

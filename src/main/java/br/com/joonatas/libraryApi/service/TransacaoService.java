package br.com.joonatas.libraryApi.service;

import br.com.joonatas.libraryApi.model.Autor;
import br.com.joonatas.libraryApi.model.GeneroLivro;
import br.com.joonatas.libraryApi.model.Livro;
import br.com.joonatas.libraryApi.repository.AutorRepository;
import br.com.joonatas.libraryApi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TransacaoService {
    //Injetando as dependências.
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LivroRepository livroRepository;
    //A transação só funciona em métodos públicos.

    @Transactional
    public void executar(){
        //Salva o autor
        Autor autor = new Autor();
        autor.setNome("teste francisco");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNasc(LocalDate.of(1970,2,23));

        autorRepository.save(autor);
        //Salva o livro

        Livro livro = new Livro();
        livro.setIsbn("4846565");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setDataLancamento(LocalDate.of(2000,01,15));
        livro.setTitulo("Teste do francisco");


        livro.setAutor(autor);
        livroRepository.saveAndFlush(livro);
        //O flush força um envio ao banco de dados.
        //é mais seguro manter o save sem o flush, por segurança na aplicação.
        //Se der algum erro na aplicação ele não persiste no BD e faz o rollback mesmo com o flush.
        //Vai verificar se existe e fazer um rollback.
        if (autor.getNome().equals("teste francisco")){
            throw new RuntimeException("rollback");
        }
        /*
        Da erro por que as alterações só são enviadas para o banco de dados no final da operação.
        Sendo assi na hora do if e ele vê que é verdadeira a condição, ele faz um rollback e desfaz toda
        a operação. não fazendo o commit.
         */
    }
}

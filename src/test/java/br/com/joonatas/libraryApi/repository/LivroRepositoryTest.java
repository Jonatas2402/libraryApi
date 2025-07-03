package br.com.joonatas.libraryApi.repository;

import br.com.joonatas.libraryApi.model.Autor;
import br.com.joonatas.libraryApi.model.GeneroLivro;
import br.com.joonatas.libraryApi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvaLivroTest(){
        Livro livro = new Livro();
        livro.setIsbn("4846565");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.AVENTURA);
        livro.setDataLancamento(LocalDate.of(2000,01,15));
        livro.setTitulo("As aventuras de lori");

        Autor autor = autorRepository
                .findById(UUID.fromString("8180a0c3-2dd0-4f77-bf19-c6e565a76d2a"))
                .orElse(null);

        livro.setAutor(autor);
        repository.save(livro);
    }

    @Test
    void atualizaTest(){
        var id = UUID.fromString("7c874e4b-c261-4ae8-a56c-c06759d55f3e");

        Optional<Livro> livroExistente = repository.findById(id);

        if (livroExistente.isPresent()){
            Livro livroEncontrado = livroExistente.get();
            System.out.println("Dados obtidos:");
            System.out.println(livroEncontrado);

            livroEncontrado.setTitulo("Adventure time");
            livroEncontrado.setPreco(BigDecimal.valueOf(250));

            repository.save(livroEncontrado);

        }
    }

    @Test
    void deleteByIdTest(){
        var id = UUID.fromString("5aa48bf6-6ae1-4117-89be-f2fd15de75f7");

        repository.deleteById(id);
    }

}

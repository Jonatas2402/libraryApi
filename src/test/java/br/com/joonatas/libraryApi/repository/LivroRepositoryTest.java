package br.com.joonatas.libraryApi.repository;

import br.com.joonatas.libraryApi.model.Autor;
import br.com.joonatas.libraryApi.model.GeneroLivro;
import br.com.joonatas.libraryApi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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
        /*Aqui o livro é salvo quando passamos o id de um autor já salvo no banco de dados*/
    }
    @Test
    void salvaCascadeTest(){
        Livro livro = new Livro();
        livro.setIsbn("4846565");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.AVENTURA);
        livro.setDataLancamento(LocalDate.of(2000,01,15));
        livro.setTitulo("As aventuras de lori");


        Autor autor = new Autor();
        autor.setNome("João");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNasc(LocalDate.of(1960,2,23));

        livro.setAutor(autor);
        repository.save(livro);
        /*Aqui utilizando o cascade, eu subo um novo livro junto com um novo autor.*/
    }
    @Test
    void salvaLivroEAutorTest(){
        Livro livro = new Livro();
        livro.setIsbn("4846565");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.AVENTURA);
        livro.setDataLancamento(LocalDate.of(2000,01,15));
        livro.setTitulo("aventura nas nuvens.");

        Autor autor = new Autor();
        autor.setNome("Marisa");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNasc(LocalDate.of(1970,2,23));

        autorRepository.save(autor);

        livro.setAutor(autor);
        repository.save(livro);
        /*Aqui utilizando o cascade, eu subo um novo livro junto com um novo autor.*/
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
    void listarLivroTest(){
        List<Livro> livros = repository.findAll();
        livros.forEach(System.out::println);
    }

    @Test
    void atualizaAutorDoLivroTest(){
        UUID id = UUID.fromString("8e1618ea-9836-416b-8fe1-b1941922b838");
        var livroParaAtualizar = repository.findById(id).orElse(null);

        var idAutor = autorRepository.findById
                        (UUID.fromString("8180a0c3-2dd0-4f77-bf19-c6e565a76d2a"))
                .orElse(null);
        livroParaAtualizar.setAutor(idAutor);
        repository.save(livroParaAtualizar);
    }

    @Test
    @Transactional // passa pelo Lazy e faz uma consulta na entidade autor.
    void buscarLivroTest(){

        var id = UUID.fromString("b34ed1fe-b476-4dfe-9eaf-10945875212c");

        var livro = repository.findById(id).orElse(null);
        System.out.println("Livro: ");
        System.out.println(livro.getTitulo());
        System.out.println("Autor: ");
        System.out.println(livro.getAutor().getNome());
    }
    @Test
    void deleteByIdTest(){
        var id = UUID.fromString("5aa48bf6-6ae1-4117-89be-f2fd15de75f7");
        repository.deleteById(id);
    }

    @Test
    void pesquisaPorTituloTest(){
        List<Livro> lista = repository.findByTitulo("As aventuras de lori");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaTituloEPrecoTest(){
        var preco = BigDecimal.valueOf(180);
        var tituloPesquisa = "Jack ryan";
        List<Livro> tituloEPreco = repository.findByTituloAndPreco(tituloPesquisa, preco);
        tituloEPreco.forEach(System.out::println);
    }

    @Test
    void buscaPorGeneroTest(){
        List<Livro>  listaGenero = repository.findByGenero(GeneroLivro.AVENTURA);
        listaGenero.forEach(System.out::println);
    }
    @Test
    void buscaLivroPorData(){
        List<Livro> buscaPorData = repository.findByDataLancamentoBetween(LocalDate.of(2000,1,15
        ), LocalDate.of(2016,6,20));
        buscaPorData.forEach(System.out::println);
    }
}

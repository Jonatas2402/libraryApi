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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class AutorRepositoryTest {
    //Essa classe é dedicada para fazer teste apenas no autor repository.

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;
    //Fazendo um teste para salvar no banco de dados.
    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Antonio");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNasc(LocalDate.of(1956,2,23));

        var autorSalvo = repository.save(autor);
        System.out.println("autor salvo: " + autorSalvo);
    }
    @Test
    public void atualizaTest(){
        var id = UUID.fromString("a451701d-d143-4744-9272-ec159f666cfa");

        Optional<Autor> possivelAutor = repository.findById(id);

        if (possivelAutor.isPresent()){
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do autor:");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNasc(LocalDate.of(1950, 4, 20));
            autorEncontrado.setNome("Sergio Almeida");
            autorEncontrado.setNacionalidade("Equador");

            repository.save(autorEncontrado);
        }
    }
    @Test
    public void listarAutorTest(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }
    @Test
    public void contaAutorTest(){
        System.out.println("contagem de autores: " + repository.count());
    }
    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("5aba62db-b738-49af-b607-b4d0c7f253f7");

        repository.deleteById(id);
    }

    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Ryan");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNasc(LocalDate.of(1990,4,20));

        Livro livro = new Livro();
        livro.setIsbn("48483679");
        livro.setPreco(BigDecimal.valueOf(250));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setDataLancamento(LocalDate.of(2018,6,25));
        livro.setTitulo("A vida de emily rolan");
        livro.setAutor(autor);


        Livro livro2 = new Livro();
        livro2.setIsbn("1845362");
        livro2.setPreco(BigDecimal.valueOf(180));
        livro2.setGenero(GeneroLivro.ACAO);
        livro2.setDataLancamento(LocalDate.of(2020,2,15));
        livro2.setTitulo("Jack ryan");
        livro2.setAutor(autor);

        //ADICIONANDO LIVRO NA LISTA DO AUTOR.
        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro2);
        autor.getLivros().add(livro);

        repository.save(autor);
        //Utilizando o cascade nao será necessario o uso do livroRepository.
    }
    @Test
    void listarLivrosDoAutor(){
        //Uma forma de trazer os dados do livro do autor sem problemas
        var id = UUID.fromString("653d6f79-d13b-4af0-8046-2b241cd58e98");
        var autor = repository.findById(id).get();

        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);
    }

    @Test
    void buscaPorNome(){
        var nomeAutor = repository.findByNome("Antonio");
        nomeAutor.forEach(System.out::println);

    }
    @Test
    void pesquisaPorNacionalidadeTest(){
        var buscaNacionalidade = repository.findByNacionalidade("Brasileiro");

        buscaNacionalidade.forEach(System.out::println);
    }
}

package br.com.joonatas.libraryApi.repository;

import br.com.joonatas.libraryApi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class AutorRepositoryTest {
    //Essa classe é dedicada para fazer teste apenas no autor repository.

    @Autowired
    AutorRepository repository;
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
        var id = UUID.fromString("2c661846-4ea8-44a0-a246-49b0c0fd8e72");

        repository.deleteById(id);
    }
}

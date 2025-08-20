package br.com.joonatas.libraryApi.repository;

import br.com.joonatas.libraryApi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {

    //Query Method
    //Pesquisa nome do autor.
    //select * from autor where nome = ?
    List<Autor> findByNome(String nome);

    //Pesquisa por nacionalidade.
    //select * from autor where nacionalidade = ?
    List<Autor> findByNacionalidade(String nacionalidade);

    List<Autor> findByNomeAndNacionalidade(String nome, String nacionalidade);

}

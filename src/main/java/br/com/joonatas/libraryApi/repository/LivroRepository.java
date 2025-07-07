package br.com.joonatas.libraryApi.repository;

import br.com.joonatas.libraryApi.model.Autor;
import br.com.joonatas.libraryApi.model.GeneroLivro;
import br.com.joonatas.libraryApi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
    //Query Method
    //select * from livro where id_autor = ?
    List<Livro> findByAutor(Autor autor);

    //Pesquisando o livro pelo titulo.
    //select * from livro where titulo = ?
    List<Livro> findByTitulo(String titulo);

    //Bscar pelo titulo e pelo preço
    //select * from livro where titulo = ? and preco = ?
    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    //Busca por Genero
    //select * from livro where genero = ?
    List<Livro> findByGenero(GeneroLivro genero);

    //Busca livro entre datas
    List<Livro> findByDataLancamentoBetween(LocalDate inicio, LocalDate fim);


}

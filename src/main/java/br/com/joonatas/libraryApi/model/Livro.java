package br.com.joonatas.libraryApi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "livro")
@Data
public class Livro {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @Column(name = "isbn", length = 20,nullable = false) //nullable = not null = não pode ficar nulo, tem que ter um valor
    private String isbn;

    @Column(name = "titulo", length = 150, nullable = false)
    private String titulo;

    @Column(name = "data_publicacao", nullable = false)
    private LocalDate dataLancamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero", length = 30,nullable = false)
    private GeneroLivro genero;

    @Column(name = "preco", precision = 18, scale = 2)
    private BigDecimal preco;
    //poderia ser utilizado BigDecimal também.

    // Fazendo relacionamento entre as tabelas.
    @ManyToOne (
            //cascade = CascadeType.ALL
            fetch = FetchType.LAZY //Esse parâmetro diz se a entidade livro leva ou não os dados do autor.
                                   //Lazy: ele não permite pegar dados do autor.
                                  //Eager: é o método default(padrão) que permite acesso a intidade autor.
     )
    // Especifica o tipo de relacionamento, nesse caso é muitos para um, um autor pode ter vários livros.
    @JoinColumn(name = "id_autor") // Diz que é uma chave estrangeira.
    private Autor autor;





}

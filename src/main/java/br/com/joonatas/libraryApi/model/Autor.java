package br.com.joonatas.libraryApi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "autor")
@Getter
@Setter
@ToString
public class Autor {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @Column(name = "nome", length = 100,nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNasc;

    @Column(name = "nacionalidade", nullable = false)
    private String nacionalidade;

   // @OneToMany(mappedBy = "autor") // Criando relacionamento do autor, nesse caso um autor para vários livros.
    //mappedBy diz como e onde a entidade autor está na entidade livro.
    @Transient
    private List<Livro> livros;
}

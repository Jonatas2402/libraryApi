package br.com.joonatas.libraryApi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "autor")
@Getter
@Setter
@Data
@ToString(exclude = "livros")
@EntityListeners(AuditingEntityListener.class)
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

   @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // Criando relacionamento do autor, nesse caso um autor para vários livros.
    //mappedBy diz como e onde a entidade autor está na entidade livro.
    private List<Livro> livros;
    @CreatedDate //Insere automaticamente a data hora atual.
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @LastModifiedDate //Sempre que um autor for atualizado ele insere a data hora atual.
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
    //private UUID idusuario;
}

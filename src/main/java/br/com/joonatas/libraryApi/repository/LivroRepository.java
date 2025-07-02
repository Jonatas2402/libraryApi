package br.com.joonatas.libraryApi.repository;

import br.com.joonatas.libraryApi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
}

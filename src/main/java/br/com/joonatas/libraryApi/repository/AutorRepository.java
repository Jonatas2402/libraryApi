package br.com.joonatas.libraryApi.repository;

import br.com.joonatas.libraryApi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {
}

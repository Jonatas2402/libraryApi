package br.com.joonatas.libraryApi.service;

import br.com.joonatas.libraryApi.model.Livro;
import br.com.joonatas.libraryApi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;


    public Livro salvar(Livro livro){
        return repository.save(livro);
    }


}

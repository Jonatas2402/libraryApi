package br.com.joonatas.libraryApi.validator;

import br.com.joonatas.libraryApi.exceptions.RegistroDuplicadoExceptions;
import br.com.joonatas.libraryApi.model.Autor;
import br.com.joonatas.libraryApi.repository.AutorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AutorValidator {
    private AutorRepository repository;

    public AutorValidator(AutorRepository repository) {
        this.repository = repository;
    }

    public void validar(Autor autor){
        //Lógica de validação.
        if (existeAutorCadastrado(autor)){
            throw new RegistroDuplicadoExceptions("Autor já cadastrado");
        }
    }

    private boolean existeAutorCadastrado(Autor autor){
        Optional<Autor> autorEncontrado = repository.findBynomeAndDataNascAndNacionalidade(autor.getNome(),
                autor.getDataNasc(),
                autor.getNacionalidade());

        //Se o id do autor for igual a nulo, isso quer dizer que esse autor não existe, será cadastrado.
        if (autor.getId() == null){
            return autorEncontrado.isPresent();
        }
        //atualizando.
        return !autor.getId().equals(autorEncontrado.get().getId()) && autorEncontrado.isPresent();
    }
}

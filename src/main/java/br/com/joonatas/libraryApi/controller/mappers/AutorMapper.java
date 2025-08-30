package br.com.joonatas.libraryApi.controller.mappers;

import br.com.joonatas.libraryApi.controller.dto.AutorDTO;
import br.com.joonatas.libraryApi.model.Autor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutorMapper {
    Autor toEntity(AutorDTO dto);

    AutorDTO toDTO(Autor autor);
}

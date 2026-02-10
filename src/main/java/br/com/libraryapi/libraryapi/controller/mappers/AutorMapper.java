package br.com.libraryapi.libraryapi.controller.mappers;

import br.com.libraryapi.libraryapi.controller.dto.AutorDTO;
import br.com.libraryapi.libraryapi.model.Autor;

public class AutorMapper {

    public static AutorDTO toDTO (Autor autor) {
        if (autor == null) return null;

        return new AutorDTO(
                autor.getId(),
                autor.getNome(),
                autor.getDataNascimento(),
                autor.getNacionalidade()
        );
    }

    public static Autor toEntity(AutorDTO dto) {
        if (dto == null) return null;

        Autor autor = new Autor();
        autor.setId(dto.id());
        autor.setNome(dto.nome());
        autor.setDataNascimento(dto.dataNascimento());
        autor.setNacionalidade(dto.nacionalidade());
        return autor;
    }


}

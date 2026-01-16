package br.com.libraryapi.libraryapi.repository;

import br.com.libraryapi.libraryapi.model.Autor;
import br.com.libraryapi.libraryapi.model.Livro;
import br.com.libraryapi.libraryapi.model.enums.GeneroLivro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTeste() {
        Livro livro = new Livro();
        livro.setIsbn("90902913-293103");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980,1,2));

        Autor autor = autorRepository.findById(UUID.fromString("91730da9-b9e4-4953-8efb-9ba9bb95bd53")).orElse(null);

        livro.setAutor(autor);

        livroRepository.save(livro);
    }
}
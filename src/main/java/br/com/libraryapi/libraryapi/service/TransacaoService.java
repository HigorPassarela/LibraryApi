package br.com.libraryapi.libraryapi.service;

import br.com.libraryapi.libraryapi.model.Autor;
import br.com.libraryapi.libraryapi.model.Livro;
import br.com.libraryapi.libraryapi.model.enums.GeneroLivro;
import br.com.libraryapi.libraryapi.repository.AutorRepository;
import br.com.libraryapi.libraryapi.repository.LivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    private AutorRepository autorRepository;

    private LivroRepository livroRepository;

    public TransacaoService(AutorRepository autorRepository, LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
    }

    @Transactional
    public void atualizacaoSemAtualizar(){
        var livro = livroRepository
                .findById(UUID.fromString("a41af312-5011-4184-b951-64252e329d97"))
                .orElse(null);

        livro.setDataPublicacao(LocalDate.of(2024,6,1));

//        livroRepository.save(livro);
    }

    @Transactional
    public void executar(){
        Autor autor = new Autor();
        autor.setNome("Francisca");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        autorRepository.save(autor);

        Livro livro = new Livro();
        livro.setIsbn("989012-111111");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Livro da Francisca");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        livro.setAutor(autor);

        livroRepository.save(livro);

        if (autor.getNome().equals("José")) {
            throw new RuntimeException("Rollback!");
        }
    }
}

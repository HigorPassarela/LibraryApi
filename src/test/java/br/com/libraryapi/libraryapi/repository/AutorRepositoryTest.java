package br.com.libraryapi.libraryapi.repository;

import br.com.libraryapi.libraryapi.model.Autor;
import br.com.libraryapi.libraryapi.model.Livro;
import br.com.libraryapi.libraryapi.model.enums.GeneroLivro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Pedro");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1930, 10, 20));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor Salvo:" + autorSalvo);
    }

    @Test
    public void atualizarTeste() {
        var id = UUID.fromString("85f357db-e716-404e-9535-31865b86c493");

        Optional<Autor> possivelAutor = repository.findById(id);

        if (possivelAutor.isPresent()) {

            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor:");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1975, 3, 10));

            repository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTeste(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de Autores:" + repository.count());
    }

    @Test
    public void deleteIdTest(){
        var id = UUID.fromString("c8ecc865-e092-410d-a5b6-59fcf46177de");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("235beb09-22a6-48d3-80d5-84858ad261cf");
        var user = repository.findById(id).get();
        repository.delete(user);
    }

    @Test
    void salvarAutorComLivroTest() {
        Autor autor = new Autor();
        autor.setNome("Antonio");
        autor.setNacionalidade("Americana");
        autor.setDataNascimento(LocalDate.of(1970, 8, 5));

        Livro livro = new Livro();
        livro.setIsbn("432913-22233");
        livro.setPreco(BigDecimal.valueOf(204));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("O Roubo da casa assombrada");
        livro.setDataPublicacao(LocalDate.of(1999, 1, 2));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("2364-11234");
        livro2.setPreco(BigDecimal.valueOf(650));
        livro2.setGenero(GeneroLivro.MISTERIO);
        livro2.setTitulo("O Roubo da casa assombrada 2");
        livro2.setDataPublicacao(LocalDate.of(2000, 1, 2));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);
        
        repository.save(autor);

        livroRepository.saveAll(autor.getLivros());
    }

    @Test
    @Transactional
    void listarLivrosAutor() {
        var id = UUID.fromString("7a930bf0-6125-4b9f-9be4-f644e01078aa");
        var autor = repository.findById(id).get();

        List<Livro> livroList = livroRepository.findByAutor(autor);
        autor.setLivros(livroList);

        autor.getLivros().forEach(System.out::println);
    }

}

package br.com.libraryapi.libraryapi.repository;

import br.com.libraryapi.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1969, 2, 20));

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
        var id = UUID.fromString("85f357db-e716-404e-9535-31865b86c493");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("235beb09-22a6-48d3-80d5-84858ad261cf");
        var user = repository.findById(id).get();
        repository.delete(user);

    }
}

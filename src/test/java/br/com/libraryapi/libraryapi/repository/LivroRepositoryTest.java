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
import java.util.List;
import java.util.UUID;

@SpringBootTest
//@Transactional
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
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Ciências");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = autorRepository
                .findById(UUID.fromString("7fe08717-e623-448a-9181-033c8980b40b"))
                .orElse(null);

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void salvarAutorELivroTeste() {
        Livro livro = new Livro();
        livro.setIsbn("989012-111111");
        livro.setPreco(BigDecimal.valueOf(180));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Ciencia Humana");
        livro.setDataPublicacao(LocalDate.of(1956, 5, 8));

        Autor autor = new Autor();
        autor.setNome("Pedro");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1930, 10, 20));

        autorRepository.save(autor);

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void salvarCascateTeste() {
        Livro livro = new Livro();
        livro.setIsbn("989012-111111");
        livro.setPreco(BigDecimal.valueOf(180));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Ciencia Humana");
        livro.setDataPublicacao(LocalDate.of(1956, 5, 8));

        Autor autor = new Autor();
        autor.setNome("Raul");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1930, 10, 20));

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void atualizarAutorDoLivro() {
        UUID id = UUID.fromString("a9db240d-0377-4620-84b7-e2981313642e");
        var livroParaAtualizar = livroRepository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("fad30683-94e5-4ea9-8905-274916a68c3c");
        Autor autor = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(autor);

        livroRepository.save(livroParaAtualizar);
    }

    @Test
    void deletar() {
        UUID id = UUID.fromString("a9db240d-0377-4620-84b7-e2981313642e");
        livroRepository.deleteById(id);
    }

    @Test
    void deletarCascate() {
        UUID id = UUID.fromString("a9db240d-0377-4620-84b7-e2981313642e");
        livroRepository.deleteById(id);
    }

    @Test
    @Transactional
    void buscarLivroTeste() {
        UUID id = UUID.fromString("112022f0-2ce4-4f95-9562-6844a0bb480c");
        Livro livro = livroRepository.findById(id).orElse(null);

        System.out.println("Livro:");
        System.out.println(livro.getTitulo());

        System.out.println("Autor:");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void pesquisaTituloTest() {
        List<Livro> lista = livroRepository.findByTitulo("O Roubo da casa assombrada");
        lista.forEach(System.out::println);

    }

    @Test
    void pesquisaIsbnTest() {
        List<Livro> lista = livroRepository.findByIsbn("90902913-293103");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorTituloEPrecoTest() {
        var preco = BigDecimal.valueOf(204.00);

        String tituloPesquisa = "O Roubo da casa assombrada";
        List<Livro> lista = livroRepository.findByTituloAndPreco(tituloPesquisa, preco);
        lista.forEach(System.out::println);
    }

    @Test
    public void listarLivrosComQueryJPQL() {
        var resultado = livroRepository.listarTodosOrdenadoPorTituloAndPreco();
        resultado.forEach(System.out::println);
    }

    @Test
    public void listarAutoresDosLivros() {
        var resultado = livroRepository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    public void listarTitulosNaoRepetidosDosLivros() {
        var resultado = livroRepository.listarNomesDiferentesLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    public void listarGenerosDeLivrosAutoresBrasileiros() {
        var resultado = livroRepository.listarGenerosAutoresBasileiros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroQueryParamTest() {
        var resultado = livroRepository.findByGenero(GeneroLivro.MISTERIO, "dataPublicacao");
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroPostionalParamTest() {
        var resultado = livroRepository.findByGeneroPositionParameters(GeneroLivro.MISTERIO, "dataPublicacao");
        resultado.forEach(System.out::println);
    }

    @Test
    void deletePorGeneroTest() {
        livroRepository.deleteByGenero(GeneroLivro.CIENCIA);
    }

//    @Test
//    void updateDataPublicacaoTest() {
//        livroRepository.updateDataPublicacao(LocalDate.of(2000,1,1));
//    }
}
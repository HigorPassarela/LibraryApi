package br.com.libraryapi.libraryapi.controller;

import br.com.libraryapi.libraryapi.controller.dto.CadastroLivroDTO;
import br.com.libraryapi.libraryapi.controller.dto.ErroResposta;
import br.com.libraryapi.libraryapi.exceptions.RegistroDuplicadoException;
import br.com.libraryapi.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/livros")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid CadastroLivroDTO dto) {
        try {
            // mapear dto para entidade
            // enviar a entidade para o service validar e salvar na base
            // criar url para acesso dos dados do livro
            // retornar codigo created com header location
            return ResponseEntity.ok(dto);
        } catch (RegistroDuplicadoException e) {
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }
}

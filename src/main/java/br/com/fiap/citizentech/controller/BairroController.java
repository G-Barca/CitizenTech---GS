package br.com.fiap.citizentech.controller;

import br.com.fiap.citizentech.dto.BairroRequest;
import br.com.fiap.citizentech.dto.BairroResponse;
import br.com.fiap.citizentech.service.BairroService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bairros")
public class BairroController {

    private final BairroService service;

    public BairroController(BairroService service) {
        this.service = service;
    }

    @GetMapping
    public Page<BairroResponse> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("/{id}")
    public BairroResponse buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BairroResponse criar(@RequestBody @Valid BairroRequest dados) {
        return service.criar(dados);
    }

    @PutMapping("/{id}")
    public BairroResponse atualizar(@PathVariable Long id, @RequestBody @Valid BairroRequest dados) {
        return service.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
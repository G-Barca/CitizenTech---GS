package br.com.fiap.citizentech.service;

import br.com.fiap.citizentech.dto.BairroRequest;
import br.com.fiap.citizentech.dto.BairroResponse;
import br.com.fiap.citizentech.exception.RecursoNaoEncontradoException;
import br.com.fiap.citizentech.model.Bairro;
import br.com.fiap.citizentech.repository.BairroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BairroService {

    private final BairroRepository repository;

    public BairroService(BairroRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<BairroResponse> listar(Pageable pageable) {
        return repository.findAll(pageable).map(BairroResponse::de);
    }

    @Transactional(readOnly = true)
    public BairroResponse buscar(Long id) {
        return BairroResponse.de(buscarEntidade(id));
    }

    @Transactional
    public BairroResponse criar(BairroRequest dados) {
        Bairro bairro = new Bairro();
        bairro.setNome(dados.nome());
        bairro.setZona(dados.zona());
        return BairroResponse.de(repository.save(bairro));
    }

    @Transactional
    public BairroResponse atualizar(Long id, BairroRequest dados) {
        Bairro bairro = buscarEntidade(id);
        bairro.setNome(dados.nome());
        bairro.setZona(dados.zona());
        return BairroResponse.de(repository.save(bairro));
    }

    @Transactional
    public void deletar(Long id) {
        repository.delete(buscarEntidade(id));
    }

    private Bairro buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Bairro não encontrado: " + id));
    }
}
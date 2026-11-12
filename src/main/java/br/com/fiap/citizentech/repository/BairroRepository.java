package br.com.fiap.citizentech.repository;

import br.com.fiap.citizentech.model.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BairroRepository extends JpaRepository<Bairro, Long> {
}
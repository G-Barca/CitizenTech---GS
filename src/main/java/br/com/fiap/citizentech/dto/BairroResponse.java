package br.com.fiap.citizentech.dto;

import br.com.fiap.citizentech.model.Bairro;
import br.com.fiap.citizentech.model.NivelRisco;

public record BairroResponse(Long id, String nome, String zona, NivelRisco nivelRisco) {

    public static BairroResponse de(Bairro b) {
        return new BairroResponse(b.getId(), b.getNome(), b.getZona(), b.getNivelRisco());
    }
}
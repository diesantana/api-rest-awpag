package com.algaworks.awpag.domain.repository;

import com.algaworks.awpag.domain.model.Parcelamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Indica que a classe anotada é um repositório
@Repository
public interface ParcelamentoRepository extends JpaRepository<Parcelamento, Long> {
}

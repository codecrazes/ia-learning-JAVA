package br.com.fiap.ia_learning.repository;

import br.com.fiap.ia_learning.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
}

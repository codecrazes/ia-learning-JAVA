package br.com.fiap.ia_learning.repository;

import br.com.fiap.ia_learning.entity.Recomendacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecomendacaoRepository extends JpaRepository<Recomendacao, Long> {
}

package br.com.fiap.ia_learning.repository;

import br.com.fiap.ia_learning.entity.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
}

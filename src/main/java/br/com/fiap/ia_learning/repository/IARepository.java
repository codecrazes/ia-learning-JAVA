package br.com.fiap.ia_learning.repository;

import br.com.fiap.ia_learning.entity.IA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IARepository extends JpaRepository<IA, Long> {
}

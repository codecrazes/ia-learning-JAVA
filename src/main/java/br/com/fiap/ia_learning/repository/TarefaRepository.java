package br.com.fiap.ia_learning.repository;

import br.com.fiap.ia_learning.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}

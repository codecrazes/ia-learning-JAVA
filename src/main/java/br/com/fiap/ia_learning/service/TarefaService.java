package br.com.fiap.ia_learning.service;

import br.com.fiap.ia_learning.dto.TarefaDto;
import br.com.fiap.ia_learning.entity.Tarefa;
import br.com.fiap.ia_learning.entity.Usuario;
import br.com.fiap.ia_learning.repository.TarefaRepository;
import br.com.fiap.ia_learning.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final UsuarioRepository usuarioRepository;

    public Tarefa criar(TarefaDto dto) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Tarefa tarefa = Tarefa.builder()
                .titulo(dto.getTitulo())
                .descricao(dto.getDescricao())
                .dificuldade(dto.getDificuldade())
                .tempoDisponivel(dto.getTempoDisponivel())
                .dataCriacao(LocalDateTime.now())
                .usuario(usuario)
                .build();

        return tarefaRepository.save(tarefa);
    }

    public Tarefa buscarPorId(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada"));
    }
}

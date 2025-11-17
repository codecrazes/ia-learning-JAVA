package br.com.fiap.ia_learning.service;

import br.com.fiap.ia_learning.dto.TarefaDto;
import br.com.fiap.ia_learning.entity.Tarefa;
import br.com.fiap.ia_learning.entity.Usuario;
import br.com.fiap.ia_learning.repository.TarefaRepository;
import br.com.fiap.ia_learning.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final UsuarioRepository usuarioRepository;
    private final MessageSource messageSource;

    private String msg(String codigo) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(codigo, null, locale);
    }

    public Tarefa criar(TarefaDto dto) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                msg("usuario.nao.encontrado")
                        )
                );

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
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                msg("tarefa.nao.encontrada")
                        )
                );
    }

    public Page<Tarefa> listarPaginado(int page, int size) {
        return tarefaRepository.findAll(PageRequest.of(page, size));
    }
}

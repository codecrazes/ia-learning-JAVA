package br.com.fiap.ia_learning.service;

import br.com.fiap.ia_learning.dto.AvaliacaoDto;
import br.com.fiap.ia_learning.entity.Avaliacao;
import br.com.fiap.ia_learning.entity.IA;
import br.com.fiap.ia_learning.entity.Usuario;
import br.com.fiap.ia_learning.repository.AvaliacaoRepository;
import br.com.fiap.ia_learning.repository.IARepository;
import br.com.fiap.ia_learning.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final IARepository iaRepository;

    public Avaliacao criar(AvaliacaoDto dto) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        IA ia = iaRepository.findById(dto.getIaId())
                .orElseThrow(() -> new EntityNotFoundException("IA não encontrada"));

        Avaliacao avaliacao = Avaliacao.builder()
                .nota(dto.getNota())
                .comentario(dto.getComentario())
                .data(LocalDateTime.now())
                .usuario(usuario)
                .ia(ia)
                .build();

        return avaliacaoRepository.save(avaliacao);
    }

    public Avaliacao buscarPorId(Long id) {
        return avaliacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada"));
    }
}

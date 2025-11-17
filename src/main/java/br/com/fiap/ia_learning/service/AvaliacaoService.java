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
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final IARepository iaRepository;
    private final MessageSource messageSource;

    private String msg(String codigo) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(codigo, null, locale);
    }

    public Avaliacao criar(AvaliacaoDto dto) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                msg("usuario.nao.encontrado")
                        )
                );

        IA ia = iaRepository.findById(dto.getIaId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                msg("ia.nao.encontrada")
                        )
                );

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
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                msg("avaliacao.nao.encontrada")
                        )
                );
    }
}

package br.com.fiap.ia_learning.service;

import br.com.fiap.ia_learning.dto.AlertaDto;
import br.com.fiap.ia_learning.entity.Alerta;
import br.com.fiap.ia_learning.entity.Usuario;
import br.com.fiap.ia_learning.repository.AlertaRepository;
import br.com.fiap.ia_learning.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AlertaService {

    private final AlertaRepository alertaRepository;
    private final UsuarioRepository usuarioRepository;
    private final MessageSource messageSource;

    private String msg(String codigo) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(codigo, null, locale);
    }

    public Alerta criar(Long usuarioId, AlertaDto dto) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                msg("usuario.nao.encontrado")
                        )
                );

        Alerta alerta = Alerta.builder()
                .mensagem(dto.getMensagem())
                .prioridade(dto.getPrioridade())
                .data(LocalDateTime.now())
                .usuario(usuario)
                .build();

        return alertaRepository.save(alerta);
    }

    public List<Alerta> listarPorUsuario(Long usuarioId) {
        return alertaRepository.findAll()
                .stream()
                .filter(a -> a.getUsuario().getId().equals(usuarioId))
                .toList();
    }
}

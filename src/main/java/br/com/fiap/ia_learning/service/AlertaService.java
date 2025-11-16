package br.com.fiap.ia_learning.service;

import br.com.fiap.ia_learning.entity.Alerta;
import br.com.fiap.ia_learning.entity.Usuario;
import br.com.fiap.ia_learning.repository.AlertaRepository;
import br.com.fiap.ia_learning.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertaService {

    private final AlertaRepository alertaRepository;
    private final UsuarioRepository usuarioRepository;

    public Alerta criar(Long usuarioId, String mensagem, String prioridade) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Alerta alerta = Alerta.builder()
                .mensagem(mensagem)
                .prioridade(prioridade)
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

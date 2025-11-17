package br.com.fiap.ia_learning.service;

import br.com.fiap.ia_learning.ai.IARecomendadorGenerativo;
import br.com.fiap.ia_learning.entity.Recomendacao;
import br.com.fiap.ia_learning.entity.Tarefa;
import br.com.fiap.ia_learning.entity.Usuario;
import br.com.fiap.ia_learning.repository.RecomendacaoRepository;
import br.com.fiap.ia_learning.repository.TarefaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class RecomendacaoService {

    private final TarefaRepository tarefaRepository;
    private final RecomendacaoRepository recomendacaoRepository;
    private final IARecomendadorGenerativo iaRecomendador;
    private final MessageSource messageSource;

    private String msg(String codigo) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(codigo, null, locale);
    }

    public Recomendacao gerarRecomendacao(Long tarefaId) {

        Tarefa tarefa = tarefaRepository.findById(tarefaId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                msg("tarefa.nao.encontrada")
                        )
                );

        Usuario usuario = tarefa.getUsuario();

        String respostaIA = iaRecomendador.gerarRecomendacao(
                usuario.getProfissao(),
                tarefa.getDescricao(),
                tarefa.getDificuldade(),
                tarefa.getTempoDisponivel()
        );

        Recomendacao recomendacao = Recomendacao.builder()
                .conteudoGerado(respostaIA)
                .dataGeracao(LocalDateTime.now())
                .tarefa(tarefa)
                .usuario(usuario)
                .build();

        return recomendacaoRepository.save(recomendacao);
    }

    public Recomendacao buscarPorId(Long id) {
        return recomendacaoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                msg("recomendacao.nao.encontrada")
                        )
                );
    }
}

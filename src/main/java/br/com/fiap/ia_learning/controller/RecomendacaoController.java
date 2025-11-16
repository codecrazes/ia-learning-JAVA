package br.com.fiap.ia_learning.controller;

import br.com.fiap.ia_learning.entity.Recomendacao;
import br.com.fiap.ia_learning.service.RecomendacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recomendacoes")
@RequiredArgsConstructor
public class RecomendacaoController {

    private final RecomendacaoService recomendacaoService;

    @PostMapping("/gerar/{tarefaId}")
    public ResponseEntity<Recomendacao> gerar(@PathVariable Long tarefaId) {
        return ResponseEntity.ok(recomendacaoService.gerarRecomendacao(tarefaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recomendacao> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(recomendacaoService.buscarPorId(id));
    }
}

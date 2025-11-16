package br.com.fiap.ia_learning.controller;

import br.com.fiap.ia_learning.dto.AvaliacaoDto;
import br.com.fiap.ia_learning.entity.Avaliacao;
import br.com.fiap.ia_learning.service.AvaliacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @PostMapping
    public ResponseEntity<Avaliacao> criar(@RequestBody AvaliacaoDto dto) {
        return ResponseEntity.ok(avaliacaoService.criar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(avaliacaoService.buscarPorId(id));
    }
}

package br.com.fiap.ia_learning.controller;

import br.com.fiap.ia_learning.dto.TarefaDto;
import br.com.fiap.ia_learning.entity.Tarefa;
import br.com.fiap.ia_learning.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<Tarefa> criar(@RequestBody TarefaDto dto) {
        return ResponseEntity.ok(tarefaService.criar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.buscarPorId(id));
    }
}

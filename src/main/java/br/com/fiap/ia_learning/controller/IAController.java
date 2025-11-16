package br.com.fiap.ia_learning.controller;

import br.com.fiap.ia_learning.entity.IA;
import br.com.fiap.ia_learning.service.IAService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ia")
@RequiredArgsConstructor
public class IAController {

    private final IAService iaService;

    @PostMapping
    public ResponseEntity<IA> criar(@RequestBody IA ia) {
        return ResponseEntity.ok(iaService.criar(ia));
    }

    @GetMapping
    public ResponseEntity<List<IA>> listar() {
        return ResponseEntity.ok(iaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IA> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(iaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IA> atualizar(@PathVariable Long id, @RequestBody IA ia) {
        return ResponseEntity.ok(iaService.atualizar(id, ia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        iaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

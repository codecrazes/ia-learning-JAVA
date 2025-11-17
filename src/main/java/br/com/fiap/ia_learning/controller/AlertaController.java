package br.com.fiap.ia_learning.controller;

import br.com.fiap.ia_learning.dto.AlertaDto;
import br.com.fiap.ia_learning.entity.Alerta;
import br.com.fiap.ia_learning.service.AlertaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alertas")
@RequiredArgsConstructor
public class AlertaController {

    private final AlertaService alertaService;

    @PostMapping("/{usuarioId}")
    public ResponseEntity<Alerta> criar(
            @PathVariable Long usuarioId,
            @RequestBody AlertaDto dto
    ) {
        return ResponseEntity.ok(alertaService.criar(usuarioId, dto));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Alerta>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(alertaService.listarPorUsuario(usuarioId));
    }
}

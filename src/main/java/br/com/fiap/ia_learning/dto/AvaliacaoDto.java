package br.com.fiap.ia_learning.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvaliacaoDto {

    private int nota;
    private String comentario;
    private Long usuarioId;
    private Long iaId;
}

package br.com.fiap.ia_learning.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecomendacaoDto {

    private Long tarefaId;
    private Long usuarioId;
    private Long iaId;

    private String conteudoGerado;
    private String insights;
    private String passos;
}

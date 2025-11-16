package br.com.fiap.ia_learning.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome da IA é obrigatório")
    private String nome;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    private String tipo; // texto, imagem, vídeo, automação...

    private Integer ecoScore; // 0 - 100

    @OneToMany(mappedBy = "ia")
    private List<Avaliacao> avaliacoes;
}

package br.com.fiap.ia_learning.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    private String dificuldade; // fácil, médio, difícil
    private Integer tempoDisponivel; // minutos ou horas

    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties({"tarefas", "recomendacoes", "avaliacoes", "alertas"})
    private Usuario usuario;

    @OneToOne(mappedBy = "tarefa", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("tarefa")
    private Recomendacao recomendacao;
}

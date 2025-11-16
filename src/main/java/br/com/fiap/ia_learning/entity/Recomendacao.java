package br.com.fiap.ia_learning.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recomendacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String conteudoGerado;

    @Column(columnDefinition = "TEXT")
    private String insights;

    @Column(columnDefinition = "TEXT")
    private String passos;

    private LocalDateTime dataGeracao;

    // RELACIONAMENTOS

    @OneToOne
    @JoinColumn(name = "tarefa_id")
    @JsonIgnoreProperties({"recomendacao", "usuario"})
    private Tarefa tarefa;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties({"tarefas", "recomendacoes", "avaliacoes", "alertas"})
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ia_id")
    @JsonIgnoreProperties({"recomendacoes"})
    private IA ia;
}

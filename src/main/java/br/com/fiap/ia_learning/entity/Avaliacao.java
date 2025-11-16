package br.com.fiap.ia_learning.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1)
    @Max(5)
    private int nota;

    private String comentario;

    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties({"tarefas", "recomendacoes", "avaliacoes", "alertas"})
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ia_id")
    @JsonIgnoreProperties({"recomendacoes", "avaliacoes"})
    private IA ia;
}

package br.com.fiap.ia_learning.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private String nome;
    private String email;
    private String senha;
    private String profissao;
}

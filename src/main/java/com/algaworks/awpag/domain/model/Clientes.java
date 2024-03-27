package com.algaworks.awpag.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Clientes {
    private Long id;
    private String nome;
    private String email;
    private String telefone;

    public Clientes(Long id, String nome, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }
}

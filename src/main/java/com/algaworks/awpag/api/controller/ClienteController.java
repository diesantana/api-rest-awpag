package com.algaworks.awpag.api.controller;

import com.algaworks.awpag.domain.model.Clientes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {
    @GetMapping("/clientes")
    public List<Clientes> listar(){
        Clientes cliente1 = new Clientes(1L, "João", "joao@email.com", "11941418851");
        cliente1.setNome("João Green");
        Clientes cliente2 = new Clientes(1L, "Maria Brown", "maria@email.com", "11948451478");
        
        return Arrays.asList(cliente1, cliente2);
    }
}

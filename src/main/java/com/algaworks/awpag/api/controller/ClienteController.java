package com.algaworks.awpag.api.controller;

import com.algaworks.awpag.domain.model.Cliente;
import com.algaworks.awpag.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

    @AllArgsConstructor
    @RestController
    public class ClienteController {
        
        private final ClienteRepository clienteRepository;
        @GetMapping("/clientes")
        public List<Cliente> listar(){
//            return clienteRepository.findByNomeContaining("Bob");
//            return clienteRepository.findByNome("Bob Brown");
            return clienteRepository.findAll();
        }
    }


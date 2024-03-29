package com.algaworks.awpag.api.controller;

import com.algaworks.awpag.domain.model.Cliente;
import com.algaworks.awpag.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
    @RestController
    @RequestMapping("/clientes")
    public class ClienteController {
        
        private final ClienteRepository clienteRepository;
        
        @GetMapping
        public List<Cliente> listar(){
            return clienteRepository.findAll();
        }
        
        @GetMapping("/{clienteId}")
        public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
            
            Optional<Cliente> cliente = clienteRepository.findById(clienteId);
            
            // Caso o optional tenha algum cliente nele
            if (cliente.isPresent()) {
                return ResponseEntity.ok(cliente.get());
            }
            
            // caso o optional esteja vazio
            return ResponseEntity.notFound().build();
        }
        
        
        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Cliente adicionar(@RequestBody Cliente cliente) {
            return clienteRepository.save(cliente);
        }
        
        @PutMapping("/{clienteId}")
        public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @RequestBody Cliente cliente) {
            if(!clienteRepository.existsById(clienteId)){
                return ResponseEntity.notFound().build();
            }
            
            cliente.setId(clienteId);
            cliente = clienteRepository.save(cliente);
            
            return ResponseEntity.ok(cliente);
        }
        
        @DeleteMapping("/{clienteId}")
        // Ao excluir não vamos ter um corpo para retornar, para o ResponseEntity passamos Void
        public ResponseEntity<Void> excluir(@PathVariable Long clienteId) {
            if(!clienteRepository.existsById(clienteId)) {
                return ResponseEntity.notFound().build();
            }
            // Executa a ação de excluir
            clienteRepository.deleteById(clienteId);
            // Retorna o código HTTP 204 noContent (sem conteúdo), 
            // significa que deu tudo certo porém não estamos retornando nenhum corpo
            return ResponseEntity.noContent().build();
            
        }
    }


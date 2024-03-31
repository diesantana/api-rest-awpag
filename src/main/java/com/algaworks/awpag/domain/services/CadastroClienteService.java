package com.algaworks.awpag.domain.services;

import com.algaworks.awpag.domain.exception.DomainException;
import com.algaworks.awpag.domain.model.Cliente;
import com.algaworks.awpag.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CadastroClienteService {
    
    private final ClienteRepository clienteRepository;
    
    public Cliente buscar(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new DomainException("Cliente não encontrado"));
    }
    
    @Transactional
    public Cliente salvar(Cliente cliente) {
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .filter(clienteOptional -> !clienteOptional.equals(cliente))
                .isPresent();
          
        if (emailEmUso) {
            throw new DomainException("Já existe um cliente cadastrado com esse email");
        }
        return clienteRepository.save(cliente);
    }
    
    @Transactional
    public void excluir(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}
 
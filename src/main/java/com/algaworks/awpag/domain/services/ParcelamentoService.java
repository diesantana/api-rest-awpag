package com.algaworks.awpag.domain.services;

import com.algaworks.awpag.domain.exception.DomainException;
import com.algaworks.awpag.domain.model.Cliente;
import com.algaworks.awpag.domain.model.Parcelamento;
import com.algaworks.awpag.domain.repository.ClienteRepository;
import com.algaworks.awpag.domain.repository.ParcelamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class ParcelamentoService {
    private final ParcelamentoRepository parcelamentoRepository;
    private final CadastroClienteService clienteService;
    
    @Transactional
    public Parcelamento cadastrar(Parcelamento novoParcelemto) {
        // verifica se o usuário está passando um id na requisição
        if(novoParcelemto.getId() != null) {
            throw new DomainException("Parcelamento a ser criado não deve possuir Id");
        }
        
        //verifica se o cliente existe, para associar o parcelamento a ele.
        Cliente cliente = clienteService.buscar(novoParcelemto.getCliente().getId());
        
        // carrega os dados do cliente existente, para exibir no resposta da requisição
        novoParcelemto.setCliente(cliente);
        
        // add a data de criação
        novoParcelemto.setDataCriacao(OffsetDateTime.now());
        
         return parcelamentoRepository.save(novoParcelemto);
    }
}

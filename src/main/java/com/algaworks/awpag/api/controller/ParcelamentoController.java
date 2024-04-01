package com.algaworks.awpag.api.controller;

import com.algaworks.awpag.api.model.ParcelamentoModel;
import com.algaworks.awpag.domain.exception.DomainException;
import com.algaworks.awpag.domain.model.Parcelamento;
import com.algaworks.awpag.domain.repository.ParcelamentoRepository;
import com.algaworks.awpag.domain.services.ParcelamentoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/parcelamentos")
public class ParcelamentoController {
    private final ParcelamentoRepository parcelamentoRepository;
    private final ParcelamentoService parcelamentoService;
    
    @GetMapping
    public List<Parcelamento> lisar() {
        return parcelamentoRepository.findAll();
    }
    
    @GetMapping("/{parcelamentoId}")
    public ResponseEntity<ParcelamentoModel> buscar(@PathVariable Long parcelamentoId) {
        return parcelamentoRepository.findById(parcelamentoId)
                .map(parcelamentoEntity -> {
                        var parcelamentoModel = new ParcelamentoModel();
                        parcelamentoModel.setId(parcelamentoEntity.getId());
                        parcelamentoModel.setNomeCliente(parcelamentoEntity.getCliente().getNome());
                        parcelamentoModel.setDescricao(parcelamentoEntity.getDescricao());
                        parcelamentoModel.setValorTotal(parcelamentoEntity.getValorTotal());
                        parcelamentoModel.setParcelas(parcelamentoEntity.getQuantidadeParcelas());
                        parcelamentoModel.setDataCriacao(parcelamentoEntity.getDataCriacao());
                        
                        return ResponseEntity.ok(parcelamentoModel);
                })
                .orElse(ResponseEntity.notFound().build()); 
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Parcelamento cadastrar(@Valid @RequestBody Parcelamento parcelamento) {
        return parcelamentoService.cadastrar(parcelamento);
    }
}

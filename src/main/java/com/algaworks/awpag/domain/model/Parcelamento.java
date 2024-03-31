package com.algaworks.awpag.domain.model;

import com.algaworks.awpag.domain.validation.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity // Espeficia que a classe é uma entidade 
@Getter // Gera os getters 
@Setter // setters 
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // Comapração é feito somento com o atributo que estiver include
public class Parcelamento {
    
    @Id                 // indetificador da classe (PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que o valor deve ser fornecido auto pelo bd
    @EqualsAndHashCode.Include  // define esse atributo na comparação no equals e hashCode
    private Long id;
    
    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
    @NotNull
    @ManyToOne
    private Cliente cliente;
    
    @NotNull
    @Size(max = 20)
    private String descricao;
    
    @NotNull
    @Positive
    private BigDecimal valorTotal;
    
    @NotNull
    @Positive
    @Max(12)
    private Integer quantidadeParcelas;
    private LocalDateTime dataCriacao;
    
}

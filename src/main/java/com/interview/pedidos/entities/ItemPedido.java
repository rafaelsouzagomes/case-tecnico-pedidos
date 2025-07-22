package com.interview.pedidos.entities;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "itemPedido")
@Data
public class ItemPedido {

	@Id
    @GeneratedValue
    private UUID id;
	
    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private Produto produto;
    
    private int quantidade;
    
    private BigDecimal valorTotal;

}

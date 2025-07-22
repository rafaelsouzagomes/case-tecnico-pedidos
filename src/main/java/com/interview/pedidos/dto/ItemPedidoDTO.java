package com.interview.pedidos.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class ItemPedidoDTO {

	   private UUID idProduto;
	   
	   private int quantidade;

}

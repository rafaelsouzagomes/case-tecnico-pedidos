package com.interview.pedidos.dto;

import java.util.List;
import java.util.UUID;

import com.interview.pedidos.enums.StatusPedido;

import lombok.Data;

@Data
public class PedidoRequest {

	  public List<ItemPedidoDTO> itensPedido;
      public UUID usuarioId;

}

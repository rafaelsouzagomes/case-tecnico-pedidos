package com.interview.pedidos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interview.pedidos.entities.ItemPedido;

public interface ItemPedidoRepository  extends JpaRepository<ItemPedido, UUID> {

}

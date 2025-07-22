package com.interview.pedidos.repository;

import com.interview.pedidos.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
} 
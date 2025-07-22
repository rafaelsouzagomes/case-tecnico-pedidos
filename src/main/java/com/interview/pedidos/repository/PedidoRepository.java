package com.interview.pedidos.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.interview.pedidos.entities.Pedido;
import com.interview.pedidos.entities.Usuario;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

    @Query("SELECT p.usuario FROM Pedido p GROUP BY p.usuario ORDER BY SUM(p.valorTotal) DESC")
    List<Usuario> findTop5UsuariosQueMaisCompraram(Pageable pageable);

    @Query("SELECT p.usuario, AVG(p.valorTotal) FROM Pedido p GROUP BY p.usuario")
    List<Object[]> findTicketMedioPorUsuario();

    @Query("SELECT COALESCE(SUM(p.valorTotal),0) FROM Pedido p WHERE p.status = 'FATURADO' AND MONTH(p.data) = MONTH(CURRENT_DATE) AND YEAR(p.data) = YEAR(CURRENT_DATE)")
    java.math.BigDecimal findValorTotalFaturadoNoMes();
    
    List<Pedido> findByUsuarioId(UUID usuarioId);
} 
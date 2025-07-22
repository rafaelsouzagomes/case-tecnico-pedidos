package com.interview.pedidos.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.pedidos.dto.ItemPedidoDTO;
import com.interview.pedidos.dto.PedidoRequest;
import com.interview.pedidos.entities.Pedido;
import com.interview.pedidos.security.JwtTokenService;
import com.interview.pedidos.service.PedidoService;
import com.interview.pedidos.service.PedidoService.EstoqueInsuficienteException;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
    private PedidoService pedidoService;
	@Autowired
	private JwtTokenService jwtTokenService;

    @PostMapping
    public ResponseEntity<?> criarPedido(@RequestBody PedidoRequest request) {
        try {
            Pedido salvo = pedidoService.criarPedido(request);
            return ResponseEntity.ok(salvo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    
    @GetMapping
    public ResponseEntity<?> getAll() {
       return ResponseEntity.ok(pedidoService.listarTodos());
    }
    
    @GetMapping("/meus-pedidos")
    public ResponseEntity<?> getByUsuarioAutenticado(HttpServletRequest request) {
    	 String token = jwtTokenService.resolveToken(request);
    	 UUID usuarioId = jwtTokenService.extractUserId(token);
    	 return ResponseEntity.ok(pedidoService.listarPorUsuario(usuarioId));
    }

    @PostMapping("/{pedidoId}/itempedido")
    public ResponseEntity<?> adicionarItemPedido(@PathVariable UUID pedidoId, @RequestBody ItemPedidoDTO itemPedidoDTO) {
        try {
            Pedido pedido = pedidoService.adicionarItem(pedidoId, itemPedidoDTO);
            return ResponseEntity.ok(pedido);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{pedidoId}/finalizar")
    public ResponseEntity<?> finalizarPedido(@PathVariable UUID pedidoId) {
        try {
            Pedido pedido = pedidoService.finalizarPedido(pedidoId);
            return ResponseEntity.ok(pedido);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{pedidoId}/pagar")
    public ResponseEntity<?> realizarPagamento(@PathVariable UUID pedidoId) {
        try {
            Pedido pedido = pedidoService.realizarPagamento(pedidoId);
            return ResponseEntity.ok(pedido);
        } catch (EstoqueInsuficienteException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> listarPedidosPorUsuario(@PathVariable UUID usuarioId) {
        try {
            return ResponseEntity.ok(pedidoService.listarPedidosPorUsuario(usuarioId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/top5-usuarios")
    public ResponseEntity<?> top5UsuariosQueMaisCompraram() {
        return ResponseEntity.ok(pedidoService.top5UsuariosQueMaisCompraram());
    }

    @GetMapping("/valor-total-faturado-mes")
    public ResponseEntity<?> valorTotalFaturadoNoMes() {
        return ResponseEntity.ok(pedidoService.valorTotalFaturadoNoMes());
    }
    
    @GetMapping("/ticket-medio")
    public ResponseEntity<?> tickedMedioDosPedidosDeCadaUsuario() {
        return ResponseEntity.ok(pedidoService.ticketMedioPorUsuario());
    }
} 
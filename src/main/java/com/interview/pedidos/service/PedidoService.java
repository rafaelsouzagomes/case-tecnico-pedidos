package com.interview.pedidos.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.interview.pedidos.dto.ItemPedidoDTO;
import com.interview.pedidos.dto.PedidoRequest;
import com.interview.pedidos.dto.TicketMedioUsuarioDTO;
import com.interview.pedidos.entities.ItemPedido;
import com.interview.pedidos.entities.Pedido;
import com.interview.pedidos.entities.Produto;
import com.interview.pedidos.entities.Usuario;
import com.interview.pedidos.enums.StatusPedido;
import com.interview.pedidos.repository.ItemPedidoRepository;
import com.interview.pedidos.repository.PedidoRepository;
import com.interview.pedidos.repository.ProdutoRepository;
import com.interview.pedidos.repository.UsuarioRepository;

@Service
public class PedidoService {
	
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;


    public Pedido criarPedido(PedidoRequest request) {
    	
		 Optional<Usuario> usuarioOpt = usuarioRepository.findById(request.usuarioId);
		 
		 if (usuarioOpt.isEmpty()) {
		     throw new RuntimeException("Usuário não encontrado");
		 }
		 
		 Usuario usuario = usuarioOpt.get();
    	
		 Pedido pedido = new Pedido();
		 pedido.setStatus(StatusPedido.PENDENTE);
		 pedido.setData(LocalDateTime.now());
		 pedido.setValorTotal(BigDecimal.ZERO);
		 pedido.setUsuario(usuario);
	        
		 return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(UUID id) {
        return pedidoRepository.findById(id);
    }

    public void deletar(UUID id) {
        pedidoRepository.deleteById(id);
    }

    public Pedido adicionarItem(UUID pedidoId, ItemPedidoDTO itemPedidoDTO) {
    	
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        
        List<ItemPedido> itensPedido =  pedido.getItensPedidos();
        addNovoItemPedido(Optional.of(pedido), itensPedido, itemPedidoDTO);
        
        pedido.setValorTotal(itensPedido.stream()
				  		   				.map(p -> p.getValorTotal() != null ? p.getValorTotal() : BigDecimal.ZERO)
				  		   				.reduce(BigDecimal.ZERO, BigDecimal::add));
        
        return pedidoRepository.save(pedido);
    }
    
	private void addNovoItemPedido( Optional<Pedido> pedidoOpt, List<ItemPedido> itens, ItemPedidoDTO itemDTO) {
		Optional<Produto> produtoOpt = produtoRepository.findById(itemDTO.getIdProduto());
		
		if(produtoOpt.isEmpty()) {
			 throw new RuntimeException("Produto não encontrado");
		}
		Produto produto = produtoOpt.get();
		
		ItemPedido item = new ItemPedido();
		item.setProduto(produto);
		item.setQuantidade(itemDTO.getQuantidade());
		item.setValorTotal(produto.getPreco().multiply(new BigDecimal(itemDTO.getQuantidade())));
		
		itemPedidoRepository.save(item);
		
		itens.add(item);
		
		produtoRepository.save(produto);
	}
	
    public Pedido finalizarPedido(UUID pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedido.setStatus(StatusPedido.AGUARDANDO_FATURAMENTO);
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidosPorUsuario(UUID usuarioId) {
        return pedidoRepository.findAll().stream()
                .filter(p -> p.getUsuario() != null && p.getUsuario().getId().equals(usuarioId))
                .toList();
    }

    public List<Usuario> top5UsuariosQueMaisCompraram() {
        return pedidoRepository.findTop5UsuariosQueMaisCompraram(PageRequest.of(0, 5));
    }
    
    public Pedido realizarPagamento(UUID pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        
        verificarEstoqueProdutos(pedido);
        atualizarEstoqueProdutos(pedido);
        
        pedido.setStatus(StatusPedido.FATURADO);
        return pedidoRepository.save(pedido);
    }

	private void atualizarEstoqueProdutos(Pedido pedido) {
		for (ItemPedido item : pedido.getItensPedidos()) {
        	Produto produto = item.getProduto();
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - item.getQuantidade());
            produtoRepository.save(produto);
        }
	}

	private void verificarEstoqueProdutos(Pedido pedido) {
		for (ItemPedido item : pedido.getItensPedidos()) {
        	Produto produto = item.getProduto();
            if (produto.getQuantidadeEmEstoque() == null || produto.getQuantidadeEmEstoque() < item.getQuantidade()) {
                pedido.setStatus(StatusPedido.CANCELADO);
                pedidoRepository.save(pedido);
                throw new EstoqueInsuficienteException("Produto sem estoque: " + produto.getNome() + ". Pedido cancelado automaticamente.");
            }
        }
	}



    public List<TicketMedioUsuarioDTO> ticketMedioPorUsuario() {
        List<Object[]> results = pedidoRepository.findTicketMedioPorUsuario();
        return results.stream()
                .map(obj -> new TicketMedioUsuarioDTO((Usuario) obj[0], (Double) obj[1]))
                .toList();
    }

	public BigDecimal valorTotalFaturadoNoMes() {
		return pedidoRepository.findValorTotalFaturadoNoMes();	
	}
	
    public static class EstoqueInsuficienteException extends RuntimeException {
        public EstoqueInsuficienteException(String message) {
            super(message);
        }
    }

	public List<Pedido> listarPorUsuario(UUID usuarioId) {
		return pedidoRepository.findByUsuarioId(usuarioId);
	}
} 
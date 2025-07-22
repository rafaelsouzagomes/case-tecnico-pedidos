package com.interview.pedidos.service;

import com.interview.pedidos.entities.Produto;
import com.interview.pedidos.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public Produto create(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listAll() {
        return produtoRepository.findAll();
    }

    public Produto update(UUID id, Produto produtoAtualizado) {
        return produtoRepository.findById(id).map(produto -> {
            produto.setNome(produtoAtualizado.getNome());
            produto.setDescricao(produtoAtualizado.getDescricao());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setCategoria(produtoAtualizado.getCategoria());
            produto.setQuantidadeEmEstoque(produtoAtualizado.getQuantidadeEmEstoque());
            return produtoRepository.save(produto);
        }).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public void delete(UUID id) {
        produtoRepository.deleteById(id);
    }

	public Produto get(UUID id) {
		 Optional<Produto> produtoOpt = produtoRepository.findById(id);
		 if(produtoOpt.isEmpty())
			 return null;
		 return produtoOpt.get();
	}
} 
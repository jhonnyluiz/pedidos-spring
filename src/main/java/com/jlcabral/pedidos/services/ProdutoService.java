package com.jlcabral.pedidos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlcabral.pedidos.domain.Produto;
import com.jlcabral.pedidos.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	public Produto buscarPorId(Long id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElse(null);
	}
}

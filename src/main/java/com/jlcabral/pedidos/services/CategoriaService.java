package com.jlcabral.pedidos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlcabral.pedidos.domain.Categoria;
import com.jlcabral.pedidos.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscarPorId(Long id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}
}

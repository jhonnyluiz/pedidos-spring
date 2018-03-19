package com.jlcabral.pedidos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlcabral.pedidos.domain.Categoria;
import com.jlcabral.pedidos.repositories.CategoriaRepository;
import com.jlcabral.pedidos.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria findById(Long id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(id.toString(), Categoria.class));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		return repo.save(obj);
	}
}

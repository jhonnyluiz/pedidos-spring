package com.jlcabral.pedidos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlcabral.pedidos.domain.Cliente;
import com.jlcabral.pedidos.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscarPorId(Long id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElse(null);
	}
}

package com.jlcabral.pedidos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlcabral.pedidos.domain.Pedido;
import com.jlcabral.pedidos.repositories.PedidoRepository;
import com.jlcabral.pedidos.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public Pedido findById(Long id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(id.toString(), Pedido.class));
	}
}

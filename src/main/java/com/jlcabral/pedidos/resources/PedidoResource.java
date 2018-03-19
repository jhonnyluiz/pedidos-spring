package com.jlcabral.pedidos.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jlcabral.pedidos.domain.Pedido;
import com.jlcabral.pedidos.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> listar(@PathVariable Long id) {
		Pedido obj = service.buscarPorId(id);
		return ResponseEntity.ok().body(obj);
	}
}

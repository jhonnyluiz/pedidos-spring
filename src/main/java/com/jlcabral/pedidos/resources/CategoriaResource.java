package com.jlcabral.pedidos.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jlcabral.pedidos.domain.Categoria;
import com.jlcabral.pedidos.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> listar(@PathVariable Long id) {
		Categoria obj = service.buscarPorId(id);
		
		return ResponseEntity.ok().body(obj);
		
//		Categoria cat1 = new Categoria(null, "Informática");
//		Categoria cat2 = new Categoria(null, "Escritório");
//		List<Categoria> lista = new ArrayList<>();
//		lista.add(cat1);
//		lista.add(cat2);
//		return lista;
	}
}

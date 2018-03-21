package com.jlcabral.pedidos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jlcabral.pedidos.domain.Cidade;
import com.jlcabral.pedidos.repositories.CidadeRepository;
import com.jlcabral.pedidos.services.exceptions.DataIntegrityException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;

	public Cidade findById(Long id) {
		Optional<Cidade> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
	public Cidade insert(Cidade obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Cidade update(Cidade obj) {
		Cidade newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Long id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma cidade que possua endereços");
		}
	}

	public List<Cidade> findAll() {
		return repo.findAll();
	}

	public Page<Cidade> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		return repo.findAll(PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
	}

	private void updateData(Cidade newObj, Cidade obj) {
		newObj.setNome(obj.getNome());
	}
}

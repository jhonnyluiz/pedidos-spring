package com.jlcabral.pedidos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jlcabral.pedidos.domain.Endereco;
import com.jlcabral.pedidos.repositories.EnderecoRepository;
import com.jlcabral.pedidos.services.exceptions.DataIntegrityException;
import com.jlcabral.pedidos.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repo;

	public Endereco findById(Long id) {
		Optional<Endereco> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(id.toString(), Endereco.class));
	}
	
	public Endereco insert(Endereco obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public List<Endereco> insertAll(List<Endereco> list) {
		return repo.saveAll(list);
	}

	public Endereco update(Endereco obj) {
		Endereco newObj = findById(obj.getId());
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

	public List<Endereco> findAll() {
		return repo.findAll();
	}

	public Page<Endereco> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		return repo.findAll(PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
	}

	private void updateData(Endereco newObj, Endereco obj) {
		newObj.setLogradouro(obj.getLogradouro());
		newObj.setNumero(obj.getNumero());
		newObj.setComplemento(obj.getComplemento());
		newObj.setBairro(obj.getBairro());
		newObj.setCep(obj.getCep());
		newObj.setCidade(obj.getCidade());
	}
}

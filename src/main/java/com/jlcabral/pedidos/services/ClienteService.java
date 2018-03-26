package com.jlcabral.pedidos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jlcabral.pedidos.domain.Cidade;
import com.jlcabral.pedidos.domain.Cliente;
import com.jlcabral.pedidos.domain.Endereco;
import com.jlcabral.pedidos.dto.ClienteDTO;
import com.jlcabral.pedidos.dto.ClienteNewDTO;
import com.jlcabral.pedidos.enuns.TipoCliente;
import com.jlcabral.pedidos.repositories.ClienteRepository;
import com.jlcabral.pedidos.services.exceptions.DataIntegrityException;
import com.jlcabral.pedidos.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private CidadeService cidadeService;

	public Cliente findById(Long id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(id.toString(), Cliente.class));
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoService.insertAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Long id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente que possua pedidos");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		return repo.findAll(PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
	}

	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cliente = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()), pe.encode(objDTO.getSenha()));
		
		Cidade cidade = cidadeService.findById(objDTO.getCidadeId());
		Endereco endereco = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cidade, cliente);
		cliente.getEnderecos().add(endereco);
		
		cliente.addTelefone(objDTO.getTelefone1());
		cliente.addTelefone(objDTO.getTelefone2());
		cliente.addTelefone(objDTO.getTelefone3());
		return cliente;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}

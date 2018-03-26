package com.jlcabral.pedidos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jlcabral.pedidos.architecture.UtilObject;
import com.jlcabral.pedidos.domain.Cliente;
import com.jlcabral.pedidos.repositories.ClienteRepository;
import com.jlcabral.pedidos.security.UserSS;

/**
 * @author Jhonny Cabral
 * @date 26 de mar de 2018
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cli = clienteRepository.findByEmail(email);
		if(UtilObject.isNull(cli)) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfis());
	}

}

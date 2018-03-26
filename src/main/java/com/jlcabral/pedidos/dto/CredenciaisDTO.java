package com.jlcabral.pedidos.dto;

import java.io.Serializable;

/**
 * @author Jhonny Cabral
 * @date 26 de mar de 2018
 */
public class CredenciaisDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String email;
	private String senha;

	public CredenciaisDTO() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}

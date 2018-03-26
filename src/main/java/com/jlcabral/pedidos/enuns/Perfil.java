package com.jlcabral.pedidos.enuns;

public enum Perfil {
	
	ADMIN(1, "ROLE_ADMINS"), 
	CLIENTE(2, "ROLE_CLIENTE");

	private Integer codigo;
	private String descricao;

	private Perfil(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Perfil toEnum(Integer codigo) {
		for (Perfil tipoCliente : Perfil.values()) {
			if (tipoCliente.getCodigo() == codigo) {
				return tipoCliente;
			}
		}

		throw new IllegalArgumentException("O codigo: " + codigo + ", é inválido.");
	}
}

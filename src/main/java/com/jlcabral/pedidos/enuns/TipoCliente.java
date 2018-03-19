package com.jlcabral.pedidos.enuns;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Física"), 
	PESSOAJURIDICA(2, "Pessoa Jurídica");

	private Integer codigo;
	private String descricao;

	private TipoCliente(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoCliente toEnum(Integer codigo) {
		for (TipoCliente tipoCliente : TipoCliente.values()) {
			if (tipoCliente.getCodigo() == codigo) {
				return tipoCliente;
			}
		}

		throw new IllegalArgumentException("O codigo: " + codigo + ", é inválido.");
	}
}

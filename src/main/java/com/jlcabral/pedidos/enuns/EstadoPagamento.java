package com.jlcabral.pedidos.enuns;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pendente"), 
	QUITADO(2, "Quitado"), 
	CANCELADO(3, "Cancelado");

	private Integer codigo;
	private String descricao;

	private EstadoPagamento(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EstadoPagamento toEnum(Integer codigo) {
		for (EstadoPagamento tipoCliente : EstadoPagamento.values()) {
			if (tipoCliente.getCodigo() == codigo) {
				return tipoCliente;
			}
		}

		throw new IllegalArgumentException("O codigo: " + codigo + ", é inválido.");
	}
}

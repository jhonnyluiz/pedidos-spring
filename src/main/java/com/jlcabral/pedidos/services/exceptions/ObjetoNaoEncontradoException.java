package com.jlcabral.pedidos.services.exceptions;

public class ObjetoNaoEncontradoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ObjetoNaoEncontradoException(String id, Class<?> clazz)  {
		super("Objeto não encontrado! ID: "+id+", Tipo do Objeto: "+ clazz.getName());
	}
	
	public ObjetoNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public ObjetoNaoEncontradoException(String msg, Throwable cause) {
		super(msg, cause);
	}
}

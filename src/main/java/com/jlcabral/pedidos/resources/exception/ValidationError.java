package com.jlcabral.pedidos.resources.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jhonny Cabral
 * @date 20 de mar de 2018
 */
public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	List<FieldMessage> erros = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	public List<FieldMessage> getErrors() {
		return erros;
	}

	public void addError(String fieldName, String message) {
		this.erros.add(new FieldMessage(fieldName, message));
	}

}

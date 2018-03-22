package com.jlcabral.pedidos.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.jlcabral.pedidos.domain.Cliente;
import com.jlcabral.pedidos.dto.ClienteDTO;
import com.jlcabral.pedidos.repositories.ClienteRepository;
import com.jlcabral.pedidos.resources.exception.FieldMessage;

/**
 * @author Jhonny Cabral
 * @date 21 de mar de 2018
 */
public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long uriId = new Long(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		
		if(aux != null && !aux.getId().equals(uriId) ) {
			list.add(new FieldMessage("email", "E-mail já existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
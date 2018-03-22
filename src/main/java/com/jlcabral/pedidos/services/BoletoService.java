package com.jlcabral.pedidos.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.jlcabral.pedidos.domain.PagamentoComBoleto;

/**
 * @author Jhonny Cabral
 * @date 22 de mar de 2018
 */
@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}
}

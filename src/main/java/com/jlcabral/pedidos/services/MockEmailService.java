package com.jlcabral.pedidos.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author Jhonny Cabral
 * @date 26 de mar de 2018
 */
public class MockEmailService extends AbstractEmailService {

	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

	@Override
	public void SendEmail(SimpleMailMessage msg) {
		LOG.info("Simulando envio de e-mail...");
		LOG.info(msg.toString());
		LOG.info("E-mail enviado");

	}

	@Override
	public void SendHtmlEmail(MimeMessage msg) {
		LOG.info("Simulando envio de e-mail HTML...");
		LOG.info(msg.toString());
		LOG.info("E-mail enviado");
	}

}

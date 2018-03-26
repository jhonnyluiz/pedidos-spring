package com.jlcabral.pedidos.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @author Jhonny Cabral
 * @date 26 de mar de 2018
 */
public class SMTPEmailService extends AbstractEmailService{

	@Autowired
	private MailSender mailSender;

	@Autowired
	private JavaMailSender javaMailSender;
	
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

	@Override
	public void SendEmail(SimpleMailMessage msg) {
		LOG.info("Enviando e-mail...");
		mailSender.send(msg);
		LOG.info("E-mail enviado");
	}

	@Override
	public void SendHtmlEmail(MimeMessage msg) {
		LOG.info("Enviando e-mail...");
		javaMailSender.send(msg);
		LOG.info("E-mail enviado");
	}

}

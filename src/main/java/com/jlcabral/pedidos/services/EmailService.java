package com.jlcabral.pedidos.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.jlcabral.pedidos.domain.Pedido;

/**
 * @author Jhonny Cabral
 * @date 26 de mar de 2018
 */
public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);

	void SendEmail(SimpleMailMessage msg);

	void sendOrderConfirmationHtmlEmail(Pedido obj);

	void SendHtmlEmail(MimeMessage msg);
}

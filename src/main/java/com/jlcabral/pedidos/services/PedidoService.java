package com.jlcabral.pedidos.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlcabral.pedidos.domain.ItemPedido;
import com.jlcabral.pedidos.domain.PagamentoComBoleto;
import com.jlcabral.pedidos.domain.Pedido;
import com.jlcabral.pedidos.enuns.EstadoPagamento;
import com.jlcabral.pedidos.repositories.ItemPedidoRepository;
import com.jlcabral.pedidos.repositories.PagamentoRepository;
import com.jlcabral.pedidos.repositories.PedidoRepository;
import com.jlcabral.pedidos.services.exceptions.ObjetoNaoEncontradoException;

/**
 * 
 * @author Jhonny Cabral
 * @date 22 de mar de 2018
 */
@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	public Pedido findById(Long id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(id.toString(), Pedido.class));
	}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.findById(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.findById(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}
}

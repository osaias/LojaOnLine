package br.com.compra;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.cliente.Endereco;
import br.com.cliente.Usuario;
import br.com.frete.Frete;

public class Pedido {

	private CarrinhoDeCompra carrinho = new CarrinhoDeCompra();
	
	private int numero = 0;
	private double total;
	private String formaPgto;
	private boolean status;
	private Endereco enderecoEntrega;
	private Frete frete;
	
	
	
	public String efetuarPedido(Compravel carrinho ,Usuario usuario, 
			Endereco entrega, TipoPgto pagamento) {
		
		if (!this.isUsuario(usuario)) {
			return "USUARIO NÃO CADASTADO...\n Crie um usuário";
		}
		
		if (usuario.getEndereco().equals(null)) {
			return false;
		}
		
		if (carrinho.getTotalItens() == 0) {
			return "CARRINHO VAZIO...\n Adicione itens ao carrinho";
		}
		
		List<ItemCarrinho> listaDeItens = new ArrayList<>();
		listaDeItens = carrinho.getItens();
		
		return false;
	}
	
	private boolean fecharPedido(Compravel carrinho ,Usuario usuario) {
		
		return false;
	}
	
	private boolean isUsuario(Usuario usuario) {
		
		if (usuario.equals(null) && (!usuario.isLogado())) {
			return false;
		}
		return true;
	}
	
	private BigDecimal calcularFrete(Endereco entrega, Compravel carrinho) {
		return null;
		
	}

}

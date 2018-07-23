package br.com.compra;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.cliente.Endereco;
import br.com.cliente.Usuario;
import br.com.frete.Frete;
import br.com.loja.Loja;
import br.com.pagamento.FormaPgto;

public class Pedido {

	private int numero;
	private double total;
	private FormaPgto formaPgto;
	private boolean status;
	private Endereco enderecoEntrega;
	private Frete frete;
	private Usuario usuario;
	private List<Produto> produtos;
	private Loja loja;
	
	
	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public FormaPgto getFormaPgto() {
		return formaPgto;
	}
	
	public void setFormaPgto(FormaPgto formaPgto) {
		this.formaPgto = formaPgto;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}
	
	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}
	
	public Frete getFrete() {
		return frete;
	}
	
	public void setFrete(Frete frete) {
		this.frete = frete;
	}
}

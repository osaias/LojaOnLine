package br.com.pagamento;

import java.math.BigDecimal;

import br.com.cliente.Usuario;
import br.com.compra.Pedido;

public abstract class Pagamento implements pagavel {

	protected long numero;
	protected Usuario usuario;
	protected Pedido pedido;
	protected BigDecimal valor;
	protected boolean pago;
	protected FormaPgto formaPgto;
	
	
	public long getNumero() {
		return numero;
	}
	public void setNumero(long numero) {
		this.numero = numero;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public boolean isPago() {
		return pago;
	}
	public void setPago(boolean status) {
		this.pago = status;
	}
	public FormaPgto getFormaPgto() {
		return formaPgto;
	}
	public void setFormaPgto(FormaPgto formaPgto) {
		this.formaPgto = formaPgto;
	}
	
	
}

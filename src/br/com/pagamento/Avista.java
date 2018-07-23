package br.com.pagamento;

import Boleto.Boleto;

public class Avista extends Pagamento {

	private double desconto;
	private Boleto boleto;
	
	
	
	public Boleto getBoleto() {
		return boleto;
	}


	public void setBoleto(Boleto boleto) {
		this.boleto = boleto;
	}


	public double getDesconto() {
		return desconto;
	}


	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}


	@Override
	public void definirFormaDePgto() {
		
		this.formaPgto = FormaPgto.AVISTA;
	}

	
}

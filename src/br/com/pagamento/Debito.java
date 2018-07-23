package br.com.pagamento;

import java.math.BigDecimal;

public class Debito extends Pagamento {

	private int numParcelas;
	private double desconto;
	private BigDecimal valorDaParcela;
	
	
	
	public int getNumParcelas() {
		return numParcelas;
	}



	public void setNumParcelas(int numParcelas) {
		this.numParcelas = numParcelas;
	}



	public double getDesconto() {
		return desconto;
	}



	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}



	public BigDecimal getValorDaParcela() {
		return valorDaParcela;
	}



	public void setValorDaParcela(BigDecimal valorDaParcela) {
		this.valorDaParcela = valorDaParcela;
	}



	@Override
	public void definirFormaDePgto() {

		this.formaPgto = FormaPgto.DEBITO;
	}

}

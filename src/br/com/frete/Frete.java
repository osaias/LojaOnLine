package br.com.frete;


import java.math.BigDecimal;
import java.util.List;

import Util.Servico;
import Util.ServicoTransporte;
import br.com.compra.Produto;
import br.com.transportadora.Transportadora;


public class Frete {
	
	private String cepOrigem;
	private String cepDestino;
	private double valorPeso;
	private BigDecimal valorFrete;
	private ServicoTransporte transportador;
	private List<Produto> produtos;
	private String servico;
	
	public String getCepOrigem() {
		return cepOrigem;
	}
	public void setCepOrigem(String cepOrigem) {
		this.cepOrigem = cepOrigem;
	}
	public String getCepDestino() {
		return cepDestino;
	}
	public void setCepDestino(String cepDestino) {
		this.cepDestino = cepDestino;
	}
	public double getValorPeso() {
		return valorPeso;
	}
	public void setValorPeso(double valorPeso) {
		this.valorPeso = valorPeso;
	}
	public BigDecimal getValorFrete() {
		return valorFrete;
	}
	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}
	public ServicoTransporte getTransportador() {
		return transportador;
	}
	public void setTransportador(ServicoTransporte transportadora) {
		this.transportador = transportadora;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
	}

	
}
	
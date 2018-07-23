package br.com.transportadora;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Util.Servico;
import Util.ServicoTransporte;
import br.com.frete.Estados;
import br.com.frete.Regioes;
import simulador.servicos.externos.ServicosCorreio.Servicos;

public abstract class Transportadora implements ServicoTransporte{

	private Long id;
	protected String nome;
	protected List<Estados> coberturaEstadual;
	protected List<Regioes> coberturaRegional;
	protected Map<Regioes,List<Estados>> cobertura;
	protected List<Servicos> servicos;
	protected Map<String,BigDecimal> fretes;
	protected String localColeta;
	protected String localEntrega;
	protected double valorPeso;
	protected double valorDistancia;

	
	public Transportadora(String nome) {
		this.nome = nome;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Estados> getCoberturaEstadual() {
		return coberturaEstadual;
	}
	public void setCoberturaEstadual(List<Estados> coberturaEstadual) {
		this.coberturaEstadual = coberturaEstadual;
	}
	public List<Regioes> getCoberturaRegional() {
		return coberturaRegional;
	}
	public void setCoberturaRegional(List<Regioes> coberturaRegional) {
		this.coberturaRegional = coberturaRegional;
	}
	public String getLocalColeta() {
		return localColeta;
	}
	public void setLocalColeta(String localColeta) {
		this.localColeta = localColeta;
	}
	public String getLocalEntrega() {
		return localEntrega;
	}
	public void setLocalEntrega(String localEntrega) {
		this.localEntrega = localEntrega;
	}
	public Map<Regioes, List<Estados>> getCobertura() {
		return cobertura;
	}
	public void setCobertura(Map<Regioes, List<Estados>> cobertura) {
		this.cobertura = cobertura;
	}
	public List<Servicos> getServicos() {
		return servicos;
	}
	public void setServicos(List<Servicos> servicos) {
		this.servicos = servicos;
	}
	public Map<String, BigDecimal> getFretes() {
		return fretes;
	}
	public void setFretes(Map<String, BigDecimal> fretes) {
		this.fretes = fretes;
	}
	public double getValorPeso() {
		return valorPeso;
	}
	public void setValorPeso(double valorPeso) {
		this.valorPeso = valorPeso;
	}
	public double getValorDistancia() {
		return valorDistancia;
	}
	public void setValorDistancia(double valorDistancia) {
		this.valorDistancia = valorDistancia;
	}
	

}

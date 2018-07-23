package Boleto;

import java.math.BigDecimal;
import java.util.List;

import br.com.BancoFinanceira.Agencia;

public class Boleto {

	private Agencia agencia;  
	private Data vencimento; 
	private Data processamento;
	private Data geracao;
	private Beneficiario beneficiario;  
	private Pagador pagador; 
	private BigDecimal valor; 
	private long numero;  
	private String instrucoes;  
	private List<LocalPgto> localPagamento;


	public Agencia getAgencia() {
		return agencia;
	}
	
	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}
	public Data getVencimento() {
		return vencimento;
	}
	public void setVencimento(Data vencimento) {
		this.vencimento = vencimento;
	}
	public Data getProcessamento() {
		return processamento;
	}
	public void setProcessamento(Data processamento) {
		this.processamento = processamento;
	}
	public Data getGeracao() {
		return geracao;
	}
	public void setGeracao(Data geracao) {
		this.geracao = geracao;
	}
	public Beneficiario getBeneficiario() {
		return beneficiario;
	}
	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}
	public Pagador getPagador() {
		return pagador;
	}
	public void setPagador(Pagador pagador) {
		this.pagador = pagador;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public long getNumero() {
		return numero;
	}
	public void setNumero(long numero) {
		this.numero = numero;
	}
	public String getInstrucoes() {
		return instrucoes;
	}
	public void setInstrucoes(String instrucoes) {
		this.instrucoes = instrucoes;
	}
	public List<LocalPgto> getLocalPagamento() {
		return localPagamento;
	}
	public void setLocalPagamento(List<LocalPgto> localPagamento) {
		this.localPagamento = localPagamento;
	}


}
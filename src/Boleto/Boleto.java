package Boleto;

import java.util.List;

public class Boleto {

	private List<String>localPagamento;
	private String localPgto;
	private Beneficiario cedente;
	private Data vencimento;
	private Data processamento;
	private Data dataDocumento;
	private int numeroDocumento;
	private String carteira;
	private String instrucoes;
	private Pagador sacado;
	private int agencia;
	private String codigoCedente;
	private int nossoNumero;
	private double valor;
	private String codigoBarras;
	
	public List<String> getLocalPagamento() {
		return localPagamento;
	}
	public void setLocalPagamento(List<String> localPagamento) {
		this.localPagamento = localPagamento;
	}
	public String getLocalPgto() {
		return localPgto;
	}
	public void setLocalPgto(String localPgto) {
		this.localPgto = localPgto;
	}
	public Beneficiario getCedente() {
		return cedente;
	}
	public void setCedente(Beneficiario cedente) {
		this.cedente = cedente;
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
	public Data getDataDocumento() {
		return dataDocumento;
	}
	public void setDataDocumento(Data dataDocumento) {
		this.dataDocumento = dataDocumento;
	}
	public int getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(int numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getCarteira() {
		return carteira;
	}
	public void setCarteira(String carteira) {
		this.carteira = carteira;
	}
	public String getInstrucoes() {
		return instrucoes;
	}
	public void setInstrucoes(String instrucoes) {
		this.instrucoes = instrucoes;
	}
	public Pagador getSacado() {
		return sacado;
	}
	public void setSacado(Pagador sacado) {
		this.sacado = sacado;
	}
	public int getAgencia() {
		return agencia;
	}
	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}
	public String getCodigoCedente() {
		return codigoCedente;
	}
	public void setCodigoCedente(String codigoCedente) {
		this.codigoCedente = codigoCedente;
	}
	public int getNossoNumero() {
		return nossoNumero;
	}
	public void setNossoNumero(int nossoNumero) {
		this.nossoNumero = nossoNumero;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
}

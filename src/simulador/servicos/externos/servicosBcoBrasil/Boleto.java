package simulador.servicos.externos.servicosBcoBrasil;

import java.math.BigDecimal;
import java.util.List;

import simulador.servicos.externos.servicosBcoBrasil.ServicosBcoBrasil.LocalPgto;

public class Boleto {

	private long numeroDocumento;
	private String numeroBanco;
	private String agenciaBeneficiario;
	private String codigoBeneficiario; 
	private String codigoBarras;
	private String carteira;
	private Endereco enderecoPagador;
	private Data vencimento; 
	private Data emissao;
	private Beneficiario beneficiario;  
	private Pagador pagador; 
	private BigDecimal valor; 
	private long nossoNumero;
	private String instrucoes;  
	private List<LocalPgto> localPagamento;
	
	public void setNumeroBanco(String numeroBanco) {
		this.numeroBanco = numeroBanco;
	}

	public String getNumeroBanco() {
		return numeroBanco;
	}

	public String getAgenciaBeneficiario() {
		return agenciaBeneficiario;
	}

	public void setAgenciaBeneficiario(String agenciaBeneficiario) {
		this.agenciaBeneficiario = agenciaBeneficiario;
	}

	public String getCodigoBeneficiario() {
		return codigoBeneficiario;
	}

	public void setCodigoBeneficiario(String codigoBeneficiario) {
		this.codigoBeneficiario = codigoBeneficiario;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getCarteira() {
		return carteira;
	}

	public void setCarteira(String carteira) {
		this.carteira = carteira;
	}

	public Endereco getEnderecoPagador() {
		return enderecoPagador;
	}

	public void setEnderecoPagador(Endereco enderecoPagador) {
		this.enderecoPagador = enderecoPagador;
	}

	public Data getVencimento() {
		return vencimento;
	}

	public void setVencimento(Data vencimento) {
		this.vencimento = vencimento;
	}

	public Data getEmissao() {
		return emissao;
	}

	public void setEmissao(Data emissao) {
		this.emissao = emissao;
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

	public long getNossoNumero() {
		return nossoNumero;
	}

	public void setNossoNumero(long nossoNumero) {
		this.nossoNumero = nossoNumero;
	}

	public long getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (numeroDocumento ^ (numeroDocumento >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Boleto other = (Boleto) obj;
		if (numeroDocumento != other.numeroDocumento)
			return false;
		return true;
	}

	
}

package Boleto;

import br.com.cliente.Endereco;

public class Beneficiario {

	private String nome;
	private Endereco endereco;
	private long numero;
	private String convenio;
	private long carteira;
	private int codigo;
	private int agencia;
	private int dvAgencia;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public long getNumero() {
		return numero;
	}
	public void setNumero(long numero) {
		this.numero = numero;
	}
	public String getConvenio() {
		return convenio;
	}
	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}
	public long getCarteira() {
		return carteira;
	}
	public void setCarteira(long carteira) {
		this.carteira = carteira;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getAgencia() {
		return agencia;
	}
	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}
	public int getDvAgencia() {
		return dvAgencia;
	}
	public void setDvAgencia(int dvAgencia) {
		this.dvAgencia = dvAgencia;
	}

	
}

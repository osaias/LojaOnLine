package br.com.cliente;

public class Endereco {

	private String logradouro;
	private String complemento;
	private int numero;
	private String cep;
	private String bairro;
	private String cidade;
	private String uf;
	private String nacionalidade;
	
	
	public Endereco(String cep, String cidade, String uf, String nacionalidade) {
		super();
		this.cep = cep;
		this.cidade = cidade;
		this.uf = uf;
		this.nacionalidade = nacionalidade;
	}

	public Endereco() {

	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public String getLogradouro() {
		return logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
	
}

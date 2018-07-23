package br.com.BancoFinanceira;

import br.com.cliente.Endereco;

public abstract class Financeira implements ServicoFinanceiro {

	protected String nome;
	protected Endereco endereco;
	//protected List<Convenio> convenios;

	public String getNome() {
		return nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}

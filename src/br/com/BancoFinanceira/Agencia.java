package br.com.BancoFinanceira;

import java.util.List;

import Util.Servico;
import Util.ServicoBancario;
import br.com.cliente.Endereco;

public abstract class Agencia implements ServicoBancario {

	protected int numeroBanco;
	protected String nome;
	protected Endereco endereco;
	protected int agencia;
	protected int dvAgencia;
	protected List<Convenio> convenios;

	public int getNumeroBanco() {
		return this.numeroBanco;
	}
	
	public String getNome() {
		return nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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

	public List<Convenio> getConvenios() {
		return convenios;
	}
	public void setConvenios(List<Convenio> convenios) {
		this.convenios = convenios;
	}
	
	
}

package br.com.BancoFinanceira;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.loja.Loja;

public class Convenio{

	private long numero;
	private String nome;
	private Loja empresa;
	private TipoConvenio tipo;
	private Agencia agencia;
	private Conta conta;
	
	public Convenio(long numero, String nome, Loja empresa, TipoConvenio tipo, Agencia agencia, Conta conta) {
		super();
		this.numero = numero;
		this.nome = nome;
		this.empresa = empresa;
		this.tipo = tipo;
		this.agencia = agencia;
		this.conta = conta;
	}
	
	public Conta getConta() {
		return this.conta;
	}

	public long getNumero() {
		return numero;
	}

	public String getNome() {
		return nome;
	}

	public Loja getEmpresa() {
		return empresa;
	}

	public TipoConvenio getTipo() {
		return tipo;
	}

	public Agencia getAgencia() {
		return agencia;
	}
	
	
}

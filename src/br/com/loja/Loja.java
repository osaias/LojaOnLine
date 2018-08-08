package br.com.loja;

import br.com.cliente.Endereco;

public class Loja {

	private String nome;
	private int numero;
	private Endereco endereco;
	
	public Loja() {
		numero = 11100212;
		nome = "Loja Online Inc.";
		endereco = new Endereco("07600-100", "Mairiporã", "SP", "Brasil");
	}
	
	public int getNumero() {
		return numero;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Endereco getEndereco() {
		return endereco;
		
	}
}

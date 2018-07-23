package br.com.loja;

import br.com.cliente.Endereco;

public class Loja {

	private static String nome;
	private static int numero;
	private static Endereco endereco;
	
	static {
		numero = 11100212;
		nome = "Loja Online";
		endereco = new Endereco("07600-100", "Mairiporã", "SP", "Brasil");
	}
	
	public static int getNumero() {
		return numero;
	}
	
	public static String getNome() {
		return nome;
	}
	
	public static Endereco getEndereco() {
		return endereco;
		
	}
}

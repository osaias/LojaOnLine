package br.com.BancoFinanceira;

public class Conta {

	private int numero;
	private int dv;
	private String cliente;
	
	public Conta(int numero, int dv, String cliente) {
		super();
		this.numero = numero;
		this.dv = dv;
		this.cliente = cliente;
	}

	public int getNumero() {
		return numero;
	}

	public int getDv() {
		return dv;
	}

	public String getCliente() {
		return cliente;
	}
	
	
}

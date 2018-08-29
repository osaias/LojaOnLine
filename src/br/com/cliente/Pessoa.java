package br.com.cliente;

public abstract class Pessoa {

	private long id;
	private String nome;
	private String rg;
	private String cpf;
	private String cnpj;
	private String sobrenome;
	private int telefone;
	private int celular;
	
	
	public Pessoa(String nome, String cpfCnpj, int telefone, int celular) {
		this.nome = nome;
		
		if (cpfCnpj.replaceAll("[^0-9]","").length() == 11) {
			this.cpf = cpfCnpj;
			this.cnpj = "";
		}
		
		if (cpfCnpj.replaceAll("[^0-9]","").length() == 14) {
			this.cnpj = cpfCnpj;
			this.cpf = "";
		}
		
		this.telefone = telefone;
		this.celular = celular;
	}
	
	public Pessoa() {
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		
		if (cpf.replaceAll("[^0-9]","").length() == 11) {
			
			this.cpf = cpf;
		} else {
			this.cpf = "";
		}
	}
	
	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
		
		if (cnpj.replaceAll("[^0-9]","").length() == 14) {
			this.cnpj = cnpj;
			
		} else {
			this.cnpj = "";
		}
	}
	
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	public int getCelular() {
		return celular;
	}
	public void setCelular(int celular) {
		this.celular = celular;
	}
	
	public static void main(String[] args) {
		String input = "320.569.108-37";
		int s = input.replaceAll("[^0-9]","").length();
		System.out.println(s);
	}
}

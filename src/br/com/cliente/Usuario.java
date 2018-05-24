package br.com.cliente;

public class Usuario extends Pessoa{

	public Usuario(String nome, String cpfCnpj) {
		super(nome, cpfCnpj);

	}
	
	public Usuario() {

	}

	private Login login;
	private Endereco endereco;
	private boolean logado;

	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	
	public boolean isLogado() {
		return logado;
	}
	public void setLogado(boolean logado) {
		this.logado = logado;
	}
}

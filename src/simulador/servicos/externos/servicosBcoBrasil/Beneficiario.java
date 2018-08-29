package simulador.servicos.externos.servicosBcoBrasil;

public class Beneficiario {
	private String nome;
	private long numero;
	private Endereco endereco;
	private String convenio;
	private String carteira;
	private int codigo;
	private int agencia;
	private int dvAgencia;

	
	public Beneficiario() {
		
		this.convenio = "Convenio Brasil Boleto";
		this.carteira = "06";
		this.codigo = 254879;
		this.agencia = 1559;
		this.dvAgencia = 1;
	}

	
	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}


	public void setCarteira(String carteira) {
		this.carteira = carteira;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}


	public void setDvAgencia(int dvAgencia) {
		this.dvAgencia = dvAgencia;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long nossoNumero) {
		this.numero = nossoNumero;
	}

	public String getConvenio() {
		return convenio;
	}

	public String getCarteira() {
		return carteira;
	}


	public int getCodigo() {
		return codigo;
	}

	public int getAgencia() {
		return agencia;
	}

	public int getDvAgencia() {
		return dvAgencia;
	}

}
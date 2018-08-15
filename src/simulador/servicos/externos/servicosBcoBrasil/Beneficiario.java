package simulador.servicos.externos.servicosBcoBrasil;

public class Beneficiario {
	private String nome;
	private long nossoNumero;
	private String convenio;
	private long carteira;
	private int codigo;
	private int agencia;
	private int dvAgencia;

	public Beneficiario() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getNossoNumero() {
		return nossoNumero;
	}

	public void setNossoNumero(long nossoNumero) {
		this.nossoNumero = nossoNumero;
	}

	public String getConvenio() {
		return convenio;
	}

	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}

	public long getCarteira() {
		return carteira;
	}

	public void setCarteira(long carteira) {
		this.carteira = carteira;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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
}
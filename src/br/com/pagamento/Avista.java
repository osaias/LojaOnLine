package br.com.pagamento;

public class Avista extends Pagamento {

	@Override
	public void definirFormaDePgto() {
		
		this.formaPgto = FormaPgto.AVISTA;
	}

	
}

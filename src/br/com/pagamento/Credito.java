package br.com.pagamento;

public class Credito extends Pagamento{

	@Override
	public void definirFormaDePgto() {

		this.formaPgto = FormaPgto.CREDITO;
	}

}

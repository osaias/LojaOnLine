package br.com.pagamento;

public class Debito extends Pagamento {

	@Override
	public void definirFormaDePgto() {

		this.formaPgto = FormaPgto.DEBITO;
	}

}

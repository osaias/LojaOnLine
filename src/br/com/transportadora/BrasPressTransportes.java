package br.com.transportadora;


import br.com.frete.Frete;
import simulador.servicos.externos.ServicosBraspress;
import simulador.servicos.externos.ServicosBraspress.Retorno;


public class BrasPressTransportes extends Transportadora {

	
	public BrasPressTransportes(String nome) {
		super(nome);
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> T getInstanciaServico() {
		ServicosBraspress servico = new ServicosBraspress();
		return (T) servico;
	}

	@Override
	public void calcularFrete(Frete frete) {

		ServicosBraspress servico = getInstanciaServico();
		if (servico.cepAtendido(frete.getCepDestino())) {
			Object retorno = servico.getFrete(frete);
			this.setFretes(((Retorno) retorno).getFretes());
			this.setValorPeso(((Retorno) retorno).getPesoTotal());

		}
	}


}

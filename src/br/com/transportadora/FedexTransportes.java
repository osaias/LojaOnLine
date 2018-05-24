package br.com.transportadora;

import br.com.frete.Frete;

import simulador.servicos.externos.ServicosFedex;
import simulador.servicos.externos.ServicosFedex.Retorno;

public class FedexTransportes extends Transportadora{

	public FedexTransportes(String nome) {
		super(nome);
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> T getInstanciaServico() {

		ServicosFedex servico = new ServicosFedex();
		return (T) servico;
	}

	@Override
	public void calcularFrete(Frete frete) {
		
		ServicosFedex servico = getInstanciaServico();
		if (servico.cepAtendido(frete.getCepDestino())) {
			Object retorno = servico.getFrete(frete);
			this.setFretes(((Retorno) retorno).getFretes());
			this.setValorPeso(((Retorno) retorno).getPesoTotal());

		}
	}

}

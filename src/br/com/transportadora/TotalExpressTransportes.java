package br.com.transportadora;

import br.com.frete.Frete;

import simulador.servicos.externos.ServicosTotalExpress;
import simulador.servicos.externos.ServicosTotalExpress.Retorno;

public class TotalExpressTransportes extends Transportadora{

	public TotalExpressTransportes(String nome) {
		super(nome);
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> T getInstanciaServico() {

		ServicosTotalExpress servico = new ServicosTotalExpress();
		return (T) servico;
	}

	@Override
	public void calcularFrete(Frete frete) {
		
		ServicosTotalExpress servico = getInstanciaServico();
		if (servico.cepAtendido(frete.getCepDestino())) {
			Object retorno = servico.getFrete(frete);
			this.setFretes(((Retorno) retorno).getFretes());
			this.setValorPeso(((Retorno) retorno).getPesoTotal());

		}
	}

}

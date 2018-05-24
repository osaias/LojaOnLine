package br.com.transportadora;

import br.com.frete.Frete;

import simulador.servicos.externos.ServicosJadlog;
import simulador.servicos.externos.ServicosJadlog.Retorno;

public class JadlogTransportes extends Transportadora{

	public JadlogTransportes(String nome) {
		super(nome);
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> T getInstanciaServico() {

		ServicosJadlog servico = new ServicosJadlog();
		return (T) servico;
	}

	@Override
	public void calcularFrete(Frete frete) {
		
		ServicosJadlog servico = getInstanciaServico();
		if (servico.cepAtendido(frete.getCepDestino())) {
			Object retorno = servico.getFrete(frete);
			this.setFretes(((Retorno) retorno).getFretes());
			this.setValorPeso(((Retorno) retorno).getPesoTotal());

		}// TODO Auto-generated method stub
		
	}

}

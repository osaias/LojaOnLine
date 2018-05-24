package Util;

import br.com.frete.Frete;

public interface Servico {

	<T> T getInstanciaServico();
	
	void calcularFrete(Frete frete);
}

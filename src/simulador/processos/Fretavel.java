package simulador.processos;

import java.util.List;

import Util.Servico;
import br.com.transportadora.Transportadora;

public interface Fretavel {

	void pesquisaCEP(String cep);
	double calcularFrete(String cep);
}

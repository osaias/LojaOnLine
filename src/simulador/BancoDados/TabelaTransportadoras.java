package simulador.BancoDados;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.transportadora.BrasPressTransportes;
import br.com.transportadora.CorreiosTransportes;
import br.com.transportadora.FedexTransportes;
import br.com.transportadora.JadlogTransportes;
import br.com.transportadora.TotalExpressTransportes;
import br.com.transportadora.Transportadora;

public class TabelaTransportadoras {
	private final static Map<Long, Transportadora> TRANSPORTADORAS = new HashMap<>();

	static {
		geraIdEAdiciona(new CorreiosTransportes("Correios"));
		geraIdEAdiciona(new BrasPressTransportes("BrasPress"));
		geraIdEAdiciona(new FedexTransportes("Fedex"));
		geraIdEAdiciona(new JadlogTransportes("JadLog"));
		geraIdEAdiciona(new TotalExpressTransportes("TotalExpress"));
	}

	private static void geraIdEAdiciona(Transportadora transportadora) {

		transportadora.setId(TRANSPORTADORAS.size() + 1l);
		TRANSPORTADORAS.put(transportadora.getId(), transportadora);
	}

	public static Collection<Transportadora> getUsuarios() {
		return TRANSPORTADORAS.values();

	}
}

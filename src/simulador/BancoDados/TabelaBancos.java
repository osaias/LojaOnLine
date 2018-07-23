package simulador.BancoDados;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.BancoFinanceira.Agencia;
import br.com.BancoFinanceira.BancoDoBrasil;
import br.com.BancoFinanceira.Bradesco;
import br.com.BancoFinanceira.Itau;

public class TabelaBancos {

	private final static Map<Integer, Agencia> BANCOS = new HashMap<>();

	static {
		geraIdEAdiciona(new BancoDoBrasil());
		geraIdEAdiciona(new Bradesco());
		geraIdEAdiciona(new Itau());

	}

	private static void geraIdEAdiciona(Agencia banco) {

		BANCOS.put(banco.getNumeroBanco(), banco);
	}

	public static Collection<Agencia> getAgencias() {
		return BANCOS.values();

	}
	
}

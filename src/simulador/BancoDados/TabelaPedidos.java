package simulador.BancoDados;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.compra.Pedido;

public class TabelaPedidos {

	private static Map<Integer, Pedido> PEDIDOS = new HashMap<>();
	
	public static boolean adiciona(Pedido pedido) {
		
		PEDIDOS.put(pedido.getNumero(), pedido);
		
		return true;
	}
	
	public static Collection<Pedido> getPedidos() {
		return PEDIDOS.values();

	}
}

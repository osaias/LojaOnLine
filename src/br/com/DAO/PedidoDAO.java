package br.com.DAO;

import br.com.compra.Pedido;
import simulador.BancoDados.TabelaPedidos;

public class PedidoDAO {

	public boolean cadastrar(Pedido pedido) {
		
		return TabelaPedidos.adiciona(pedido);
		
	}

}

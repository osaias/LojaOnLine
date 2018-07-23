package br.com.DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.transportadora.Transportadora;
import simulador.BancoDados.TabelaTransportadoras;

public class TransportadoraDAO {

	public List<Transportadora> getTransportadorasConveniadas() {
		
		Iterator<Transportadora> iterator = TabelaTransportadoras.getUsuarios().iterator();
		List<Transportadora> lista = new ArrayList<>();

		while(iterator.hasNext()) {
			   lista.add(iterator.next());
			}
		return lista;
	}
}

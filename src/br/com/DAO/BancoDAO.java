package br.com.DAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.BancoFinanceira.Agencia;
import simulador.BancoDados.TabelaBancos;

public class BancoDAO {

	public List<Agencia> getBancosConveniadas() {

		Collection<Agencia> agencias = TabelaBancos.getAgencias();
		List<Agencia> lista = new ArrayList<>();

		for (Agencia agencia : agencias) {
			lista.add(agencia);
		}
		return lista;
	}
}

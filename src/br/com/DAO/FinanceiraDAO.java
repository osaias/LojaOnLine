package br.com.DAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.BancoFinanceira.Financeira;
import simulador.BancoDados.TabelaFinanceiras;

public class FinanceiraDAO {

	public List<Financeira> getFinanceiras(){
		
		 Collection<Financeira> financeiras = TabelaFinanceiras.getFinanceiras();
		 List<Financeira> lista = new ArrayList<>();
		 
		 for (Financeira financeira : financeiras) {
			lista.add(financeira);
		}
		 
		 return lista;
	}
}

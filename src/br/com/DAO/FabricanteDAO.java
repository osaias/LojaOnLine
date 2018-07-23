package br.com.DAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.estoque.Fabricante;
import simulador.BancoDados.TabelaFabricantes;

public class FabricanteDAO {

	public List<Fabricante> getFabricantes(){
		
		 Collection<Fabricante> fabricantes = TabelaFabricantes.getFabricantes();
		 List<Fabricante> lista = new ArrayList<>();
		 
		 for (Fabricante fabricante : fabricantes) {
			lista.add(fabricante);
		}
		 
		 return lista;
	}
}

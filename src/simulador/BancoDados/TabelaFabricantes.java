package simulador.BancoDados;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.compra.Produto;
import br.com.estoque.Fabricante;

public class TabelaFabricantes {

	private final static Map<Long, Fabricante> FABRICANTES = new HashMap<>();
	
	static {
		
		gerarListaDeFabricantes();
	}

	private static void gerarListaDeFabricantes() {
		
		Collection<Produto> produtos = TabelaProdutos.getProdutos();
		Set<String> fabricantes = new HashSet<>();
		long id = 1;
		
		for (Produto produto : produtos) {
			
			fabricantes.add(produto.getFabricante());
		}
		
		for (String nome : fabricantes) {
			
			Fabricante fabricante = new Fabricante();
			
			fabricante.setId(id++);
			fabricante.setNome(nome);
			FABRICANTES.put(fabricante.getId(), fabricante);
		}
	}
	
	
	public static void main(String[] args) {
		new TabelaFabricantes();
		TabelaFabricantes.gerarListaDeFabricantes();
		TabelaFabricantes.FABRICANTES.values();
	}
	
	public static Collection<Fabricante> getFabricantes() {
		return FABRICANTES.values();
	}
}

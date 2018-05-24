package simulador.BancoDados;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.compra.Produto;
import br.com.estoque.Fabricante;

public class TabelaFabricanteProdutos {

	private final static Map<Fabricante,List<Produto>> FABSEPRODS = new HashMap<>();
	
	static {
		gerarListaDeProdutosPorFabricante();
	}

	private static void gerarListaDeProdutosPorFabricante() {
		
		Collection<Fabricante> fabricantes = new TabelaFabricantes().getFabricantes();
		Collection<Produto> produtos = new TabelaProdutos().getProdutos();
		
		for (Fabricante fabricante : fabricantes) {
			
			List<Produto> produtosDoFabricante = new ArrayList<>();
			
			for (Produto produto : produtos) {
				
				if(fabricante.getNome().equals(produto.getFabricante())){
					produtosDoFabricante.add(produto);
				}
			}
			
			FABSEPRODS.put(fabricante, produtosDoFabricante);
		}
	}
	
	public static Map<Fabricante, List<Produto>> getProdutosPorFabricante() {
		return FABSEPRODS;
	}
	
	public static void main(String[] args) {
		
		new TabelaFabricanteProdutos().gerarListaDeProdutosPorFabricante();
		System.out.println(FABSEPRODS.keySet().size());
	}
}

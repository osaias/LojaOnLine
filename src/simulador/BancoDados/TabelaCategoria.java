package simulador.BancoDados;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.compra.Produto;
import br.com.estoque.Categoria;
import br.com.estoque.Categorias;

public class TabelaCategoria {
	
	private final static Map<Categoria,List<Produto>> CATEGORIAS = new HashMap<>();
	
	static {
		popula();
	}

	private static void popula() {

		Set<Categorias> categorias = new HashSet<>();
		
		categorias = gerarListaDeCategorias();
		
		if(categorias != null) {
			
			for (Categorias nome : categorias) {
				
				Categoria categoria = new Categoria();
				
				categoria.setNome(nome);
				
				List<Produto> produtos = pegarProdutosCategoria(nome);
				
				CATEGORIAS.put(categoria , produtos);
			}
		}
	}
	
	private static List<Produto> pegarProdutosCategoria(Categorias categoria) {

		Collection<Produto> produtos = TabelaProdutos.getProdutos();
		List<Produto> lista = new ArrayList<>();

		for (Produto produto : produtos) {

			if (produto.getNome().toLowerCase().contains(categoria.name().toLowerCase()) 
					|| produto.getDescricao().toLowerCase().contains(categoria.name().toLowerCase())) {

				lista.add(produto);
			}
		}
		
		return lista;
	}

	private static Set<Categorias> gerarListaDeCategorias() {

		Set<Categorias> lista = new HashSet<>();
		Collection<Produto> produtos = TabelaProdutos.getProdutos();
		
		for (Produto produto : produtos) {
			
			Categorias[] nomeCategorias = Categorias.values();
			
			for (int i=0; i < nomeCategorias.length; i++) {
				
				if (produto.getNome().toLowerCase().contains(nomeCategorias[i].name().toLowerCase()) 
						|| produto.getDescricao().toLowerCase().contains(nomeCategorias[i].name().toLowerCase())) {
					
					lista.add(nomeCategorias[i]);
				}
			}
		}
		
		return lista;
	}
	
	public static Map<Categoria,List<Produto>> getCategorias(){
		return CATEGORIAS;
	}
	
	public static void main(String[] args) {
		Produto produto = new Produto("Tv Sony", "40' Full HD Smart", "Sony", 2000.00, 100.0, 10.0, 150.00, 3250.00);
		TabelaCategoria.popula();
	}
}

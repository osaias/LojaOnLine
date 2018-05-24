package simulador.BancoDados;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.compra.Produto;
import br.com.estoque.Categoria;
import br.com.estoque.Setor;
import br.com.estoque.Setores;

public class TabelaSetores {

	private final static Map<Setor,List<Produto>> SETORES = new HashMap<>();
	
	static {
		
		popula();
	}

	private static void popula() {
		
		Map<Categoria, List<Produto>> categorias = new TabelaCategoria().getCategorias();
		
		Set<Setores> setores = geraListaDeSetores(categorias);
		
		for (Setores nome : setores) {
			
			Setor setor = new Setor();
			
			setor.setNome(nome);
			
			SETORES.put(setor, pegarProdutosDoSetor(nome, categorias));
		}
		
	}
	
	private static List<Produto> pegarProdutosDoSetor(Setores nome, Map<Categoria, List<Produto>> categorias) {
		
		Set<Categoria> chaves = categorias.keySet();
		List<Produto> produtos = new ArrayList<Produto>();
		for (Categoria categoria : chaves) {
			
			if (categoria.getNome().getSetor().equals(nome)) {
				produtos.addAll(categorias.get(categoria));
			}
		}
		return produtos;
	}

	private static Set<Setores> geraListaDeSetores(Map<Categoria, List<Produto>> categorias) {
		
		Set<Setores> lista = new HashSet<>();

		Set<Categoria> chaves = categorias.keySet();
		
		for (Categoria categoria : chaves) {
			
			lista.add(categoria.getNome().getSetor());
		}
		
		return lista;
	}
	
	public static Map<Setor, List<Produto>> getSetores(){
		return SETORES;
	}
	
	public static void main(String[] args) {
		new TabelaSetores();
	}
}

package br.com.estoque;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.DAO.ProdutoDAO;
import br.com.cliente.Endereco;
import br.com.compra.Produto;
import simulador.BancoDados.TabelaCategoria;
import simulador.BancoDados.TabelaFabricanteProdutos;
import simulador.BancoDados.TabelaFabricantes;
import simulador.BancoDados.TabelaProdutos;
import simulador.BancoDados.TabelaSetores;

/*
 * É montado a partir da tabela de produtos
 */
public final class Estoque {

	private static Endereco endereco;
	private static Map<Setor,List<Produto>> setores;
	private static Map<Categoria, List<Produto>> categorias;
	private static Map<Fabricante, List<Produto>> produtosDoFabricante;
	private static Collection<Fabricante> fabricantes;
	private static Collection<Produto> produtos;
	
	static {

		endereco = new Endereco("07600-100", "Mairiporã", "SP", "Brasil");
		setores = TabelaSetores.getSetores();
		categorias = TabelaCategoria.getCategorias();
		fabricantes = TabelaFabricantes.getFabricantes();
		produtos = TabelaProdutos.getProdutos();
		produtosDoFabricante = TabelaFabricanteProdutos.getProdutosPorFabricante();
	}


	public static Endereco getEndereco() {
		return endereco;
	}


	public static Map<Setor, List<Produto>> getSetores() {
		return setores;
	}


	public static Map<Categoria, List<Produto>> getCategorias() {
		return categorias;
	}


	public static Map<Fabricante, List<Produto>> getProdutosDoFabricante() {
		return produtosDoFabricante;
	}


	public static List<Fabricante> getFabricantes() {
		
		return new ArrayList<>(fabricantes);
	}


	public static List<Produto> getProdutos() {
		return new ArrayList<>(produtos);
	}
		
}

package br.com.DAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import br.com.compra.Produto;
import simulador.BancoDados.TabelaProdutos;

public class ProdutoDAO {

	public Produto consultar(Produto produto) {

		Collection<Produto> produtos = TabelaProdutos.getProdutos();
		if(produtos.contains(produto)) {
			Iterator<Produto> iterator = produtos.iterator();

			while(iterator.hasNext()) {
				if (iterator.next().equals(produto)) {
					return iterator.next();
				}
			}
		}

		return null;
	}

	public Produto pegarProduto(Produto produto) {

		return pegarProdutos(produto, 1).get(0);
	}

	private void baixaNoEstoque(List<Produto> prodsParaRemocao) {

		for (Produto produto : prodsParaRemocao) {
			TabelaProdutos.remover(produto);
		}
	}

	public List<Produto> pegarProdutos(Produto produto, int quantidade) {

		List<Produto> prodsParaRetorno = new ArrayList<>();
		List<Produto> prodsParaRemocao = new ArrayList<>();
		Collection<Produto> produtos = TabelaProdutos.getProdutos();

		if (quantidade > consultarQuantidade(produto)) {
			throw new IllegalArgumentException("N�o existem produtos suficientes");
		}

		for (Produto prod : produtos) {

			if (produto.getNome().equals(prod.getNome()) && prodsParaRetorno.size() < quantidade) {
				prodsParaRetorno.add(prod);
				prodsParaRemocao.add(prod);
			}
		}

		baixaNoEstoque(prodsParaRemocao);
		
		return prodsParaRetorno;
	}

	public int consultarQuantidade(Produto produto) {

		int qtd = 0;
		Collection<Produto> produtos = TabelaProdutos.getProdutos();
		Iterator<Produto> iterator = produtos.iterator();

		while (iterator.hasNext()) {
			if (iterator.next().getNome().equals(produto.getNome())) {
				qtd++;
			}
		}

		return qtd;
	}

	public List<Produto> getTodosProdutos(){

		List<Produto> lista = new ArrayList<>();
		Collection<Produto> produtos = TabelaProdutos.getProdutos();

		lista.addAll(produtos);
		return lista;
	}



	public static void main(String[] args) {

		Produto produto = new Produto("Tv Sony", "40' Full HD Smart", "Sony", 2000.00, 100.0, 10.0, 150.00, 3250.00);
		produto.setId(1);
		ProdutoDAO dao = new ProdutoDAO();

		System.out.println(dao.consultarQuantidade(produto));
		System.out.println(dao.consultar(produto).getNome());
		dao.pegarProdutos(produto,5);
	}
}

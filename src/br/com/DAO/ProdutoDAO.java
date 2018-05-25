package br.com.DAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
		return this.consultar(produto);
	}
	
	public List<Produto> pegarProdutos(Produto produto, int quantidade) {
		
		List<Produto> lista = new ArrayList<>();
		Collection<Produto> produtos = TabelaProdutos.getProdutos();
		
		if (quantidade > consultarQuantidade(produto)) {
			throw new IllegalArgumentException("Não existem produtos suficientes");
		}
		
		for (Produto prod : produtos) {
			
			if (produto.getNome().equals(prod.getNome()) && lista.size() <= quantidade) {
				lista.add(prod);
			}
		}
		
		return lista;
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
	}
}

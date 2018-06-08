package br.com.compra;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarrinhoDeCompra implements Compravel{
	
	private Set<ItemCarrinho> itens = new HashSet<>();

	@Override
	public void adiciona(ItemCarrinho item) {

		this.itens.add(item);
	}

	@Override
	public boolean remove(ItemCarrinho item) {
		
		if (itens.contains(item)) {
			return this.itens.remove(item);
		}

		return false;
	}

	@Override
	public double getPrecoTotal() {

		double total = 0;
		for (ItemCarrinho item : itens) {
			total += item.getValor();
		}
		return total;
	}
	
	public Set<ItemCarrinho> getItens(){
		return this.itens;
	}
	
	public List<Produto> getProdutos(){
		
		List<Produto> lista = new ArrayList<>();
		
		for (ItemCarrinho produto : itens) {
			lista.add(produto.getProduto());
		}
		return lista;
	}

	@Override
	public int getTotalItens() {
		return this.itens.size();
	}
	
}

package br.com.compra;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoDeCompra implements Compravel{
	
	private List<ItemCarrinho> itens = new ArrayList<>();

	@Override
	public void adiciona(ItemCarrinho item, int quantidade) {

		item.setQuantidade(quantidade);
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
			total += item.getValorTotal();
		}
		return total;
	}
	
	public List<ItemCarrinho> getItens(){
		return this.itens;
	}

	@Override
	public int getTotalItens() {
		return this.itens.size();
	}
	
}

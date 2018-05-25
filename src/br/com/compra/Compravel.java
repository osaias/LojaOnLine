package br.com.compra;

import java.util.List;

public interface Compravel {

	public void adiciona(ItemCarrinho item);
	public boolean remove(ItemCarrinho item);
	public double getPrecoTotal();
	public int getTotalItens();
	public List<ItemCarrinho> getItens();
}

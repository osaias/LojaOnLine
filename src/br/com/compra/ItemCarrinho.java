package br.com.compra;

public class ItemCarrinho {

	private Produto produto;

	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public double getValor() {
		return this.getProduto().getPreco();
	}

}

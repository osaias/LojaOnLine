package br.com.estoque;

import java.util.ArrayList;
import java.util.List;

import br.com.compra.Produto;

public class Fabricante {
	
	private long id;
	private String nome;
	private List<Produto> produtosFabricados;
	
	public Fabricante() {
		this.produtosFabricados = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Produto> getProdutosFabricados() {
		return produtosFabricados;
	}

	public void setProdutosFabricados(List<Produto> produtosFabricados) {
		this.produtosFabricados = produtosFabricados;
	}
	
	
	
	
}

package br.com.estoque;

public enum Categorias {

	TV (Setores.ELETROELETRONICOS),
	SOM (Setores.ELETROELETRONICOS),
	REFRIGERADOR (Setores.ELETRODOMESTICOS),
	RELOGIO (Setores.ACESSORIOS),
	NOTEBOOK (Setores.INFORMATICA),
	HD (Setores.INFORMATICA),
	MOUSE (Setores.INFORMATICA),
	TECLADO (Setores.INFORMATICA),
	MONITOR (Setores.INFORMATICA),
	MICROONDAS (Setores.ELETRODOMESTICOS),
	SMARTPHONE (Setores.TELEFONIA);
	
	private Setores setor;

	
	private Categorias(Setores setor) {
		this.setor = setor;
	}


	public Setores getSetor() {
		return setor;
	}
	
	
}

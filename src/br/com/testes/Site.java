package br.com.testes;

import br.com.compra.Produto;
import simulador.processos.PaginaPrincipal;
import simulador.processos.PaginaProduto;

public class Site {

	public static void main(String[] args) {
		
		/*
		 * Existe a opção de instanciar os objetos aqui, já que são publicos
		 * NOTA: A implementação usada foi de composiçao
		 */
		boolean repetir = true;
		
		PaginaPrincipal pagPrinc = new PaginaPrincipal();
		PaginaProduto pagProd = new PaginaProduto();
		
		do {
			
			Produto produto = pagPrinc.listarProdutos();
			pagPrinc.clicar(produto);
			
				
		} while (repetir = true);
		
	}

}

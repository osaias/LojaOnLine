package br.com.testes;

import br.com.compra.Produto;
import simulador.processos.PaginaPrincipal;
import simulador.processos.PaginaProduto;

public class Site {

	public static void main(String[] args) {
		
		/*
		 * Existe a op��o de instanciar os objetos aqui, j� que s�o publicos
		 * NOTA: A implementa��o usada foi de composi�ao
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

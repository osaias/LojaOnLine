package simulador.processos;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Util.Console;
import br.com.cliente.Usuario;
import br.com.compra.CarrinhoDeCompra;
import br.com.compra.ItemCarrinho;
import br.com.compra.Produto;
import br.com.frete.Frete;

public class PaginaCarrinho {

	/*
	 * lista de produtos adicionados
	 * calculo do frete
	 */
	private CarrinhoDeCompra carrinho = new CarrinhoDeCompra();
	
	public void efetuarCompra(Produto produto, Map<String, BigDecimal> fretes, Usuario usuario) {
		
		//ainda mostrando a lista do metodo anterior
		exibirListaProduto(selecionarQuantidade(produto));
		
		
		;
		
		
	}
	
	private List<ItemCarrinho> selecionarQuantidade(Produto produto) {
		
		System.out.println("\n\n");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.print("   QUANTIDADE: ");
		
		Scanner entrada = new Scanner(System.in);
		int qtd= Integer.parseInt(entrada.nextLine());
		
		ItemCarrinho item = new ItemCarrinho();
		item.setProduto(produto);
		item.setQuantidade(quantidade);
	}
	
	private Frete selecionarFrete(Map<String, BigDecimal> fretes) {
		
		return null;
		
	}
	
	private void exibirFormasPgto(Produto produto) {
		System.out.println("\n\n");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println(" À VISTA............: R$ " + String.format("%1$.2f", (produto.getPreco() - produto.getPreco() * 0.1)));
		System.out.println(" PARCELADO..(12x )..: R$ " + String.format("%1$.2f",(produto.getPreco() / 12)));
		System.out.println("--------------------------------------------------------------------------------");
	}
	
	private void exibirListaProduto(List<Produto> produtos) {
		
		Console.limpatela();
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("                                                  4 - LOGIN / 5 - CADASTRE-SE");
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("\n\n\n\n");
		System.out.println(String.format("%1$-25s %2$-40s %3$-9s", "NOME", "DESCRIÇAO", "VALOR"));
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println(String.format("%1$-25s %2$-40s %3$.2f", produto.getNome().toUpperCase(), produto.getDescricao(), produto.getPreco()));

		exibirFormasPgto(produto);

		System.out.println("\n\n");
		System.out.println("     1 - VOLTAR         2 - COMPRAR \n");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("                                                         3 - CALCULAR FRETE");
		System.out.println("--------------------------------------------------------------------------------");
		
		
		
		
		
		
		
	}
}

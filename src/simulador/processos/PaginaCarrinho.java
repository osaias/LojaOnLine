package simulador.processos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import Util.Console;
import Util.Sessao;
import br.com.DAO.ProdutoDAO;
import br.com.cliente.Usuario;
import br.com.compra.CarrinhoDeCompra;
import br.com.compra.ItemCarrinho;
import br.com.compra.Produto;


public class PaginaCarrinho {

	private CarrinhoDeCompra carrinho;
	private ProdutoDAO dao = new ProdutoDAO();
	
	public void adicionarNoCarrinho(Produto produto) {

		Map<String, BigDecimal> fretes = (Map<String, BigDecimal>) Sessao.getAtributo("fretes");
		Usuario usuario = (Usuario) Sessao.getAtributo("usuario");

		carrinho = (CarrinhoDeCompra) Sessao.getAtributo("carrinho");

		if (carrinho == null) {
			carrinho = new CarrinhoDeCompra();
		}
		
		ItemCarrinho item= new ItemCarrinho();
		item.setProduto(dao.pegarProduto(produto));

		carrinho.adiciona(item);

		

		Sessao.setAtributo("carrinho", carrinho);

	}

	public void removerDoCarrinho() {
		
		System.out.println();
		System.out.println("--------------------------------------------------------------------------------");
		System.out.print(" REMOVER PRODUTO <nome>: ");
		Scanner entrada = new Scanner(System.in);
		String nome = entrada.nextLine();
		System.out.println("--------------------------------------------------------------------------------");
		
		carrinho = (CarrinhoDeCompra) Sessao.getAtributo("carrinho");
		CarrinhoDeCompra carrinhoAux = carrinho;
		
		if (carrinho != null) {
			
			Set<ItemCarrinho> itens = carrinho.getItens();
			for (ItemCarrinho item : itens) {
				
				if (item.getProduto().getNome().equalsIgnoreCase(nome)) {
					carrinhoAux.remove(item);
					break;
				}
			}
			
			Sessao.setAtributo("carrinho", carrinhoAux);
		}
	}
	
	public void selecionarQuantidade() {
		
		Scanner entrada = new Scanner(System.in);
		List<Produto> produtos = new ArrayList<>();
		
		System.out.println("\n");
		System.out.println("=====================");
		System.out.print(" Produto: ");
		String nome = entrada.nextLine();
		System.out.println("=====================");
		System.out.print("   Quantidade: ");
		int qtd= Integer.parseInt(entrada.nextLine());
		System.out.println("=====================");
		
		carrinho = (CarrinhoDeCompra) Sessao.getAtributo("carrinho");
		
		Set<ItemCarrinho> itens = carrinho.getItens();
		for (ItemCarrinho item : itens) {
			
			if (item.getProduto().getNome().equalsIgnoreCase(nome)) {
				produtos = dao.pegarProdutos(item.getProduto(), qtd);
				break;
			}
		}
		
		for (Produto prod : produtos) {
			
			ItemCarrinho item = new ItemCarrinho();
			item.setProduto(prod);
		
			carrinho.adiciona(item);
		}
		
		Sessao.setAtributo("carrinho", carrinho);
	}
	
	private void exibirFormasPgto(CarrinhoDeCompra carrinho) {
		
		double aVista = 0, parcelado = 0;
		Set<ItemCarrinho> itens = carrinho.getItens();
		for (ItemCarrinho item : itens) {
			
			aVista += item.getProduto().getPreco() - item.getProduto().getPreco() * 0.1;
			parcelado += item.getProduto().getPreco() / 12;
		}
		
		System.out.println("\n\n");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println(" À VISTA............: R$ " + String.format("%1$.2f", aVista));
		System.out.println(" PARCELADO..(12x )..: R$ " + String.format("%1$.2f",parcelado));
		System.out.println("--------------------------------------------------------------------------------");
	}
	
	public void exibirListaProduto() {
		
		Sessao.setAtributo("paginaAtual", "PaginaCarrinho");
		
		Usuario usuario = (Usuario) Sessao.getAtributo("usuario");
		
		Console.limpatela();
		System.out.println("=================================================================================");
		System.out.println("                               PAGINA DO CARRINHO                                ");
		System.out.println("=================================================================================");
		if (usuario == null) {
			System.out.println("                                                  4 - LOGIN / 5 - CADASTRE-SE");
		} else {
			System.out.println(String.format("%1$-52s %2$-10s %3$-15s"," ", "Bem Vindo: ", usuario.getNome()));
		}
		System.out.println("\n");
		System.out.println(String.format("%1$-25s %2$-40s %3$-9s %4$-3s", "NOME", "DESCRIÇAO", "VALOR", "QTD"));
		System.out.println("---------------------------------------------------------------------------------");
		
		carrinho = (CarrinhoDeCompra) Sessao.getAtributo("carrinho");
		
		List<ItemCarrinho> lista = new ArrayList<>();
		List<ItemCarrinho> produtos = new ArrayList<>();
		Set<ItemCarrinho> itens = carrinho.getItens();
		lista.addAll(itens);
		//mecher aqui
		produtos.add(0, lista.get(0));
		
		for (ItemCarrinho item : itens) {
			
			for (int i = 0; i < produtos.size(); i++) {
				if (!item.getProduto().getNome().equals(produtos.get(i).getProduto().getNome())) {
					produtos.add(produtos.get(i));
				}
			}
		}

		produtosParaExibir(produtos);
		exibirFormasPgto(carrinho);

		System.out.println();
		System.out.println("     1 - VOLTAR         9 - COMPRAR \n");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("                                                         3 - CALCULAR FRETE");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("    6 - Adicionar            7 - Remover          8 - Selecionar quantidade"     );
		System.out.println("================================================================================");
	}
	
	private void produtosParaExibir(List<ItemCarrinho> itens) {
		
		Sessao.setAtributo("listaDeProdutos", itens);
		
		for (ItemCarrinho produto : itens) {
			
			System.out.println(String.format("%1$-25s %2$-40s %3$.2f  %4$03d", produto.getProduto().getNome().toUpperCase(),
					produto.getProduto().getDescricao(), produto.getProduto().getPreco(), quantidade(produto)));
		}
	}

	private int quantidade(ItemCarrinho item) {
		
		int qtd = 0;
		carrinho = (CarrinhoDeCompra) Sessao.getAtributo("carrinho");
		
		Set<ItemCarrinho> itens = carrinho.getItens();
		for (ItemCarrinho it : itens) {
			
			if (it.getProduto().getNome().equals(item.getProduto().getNome())) {
				qtd++;
			}
		}
		
		return qtd;
	}
	
	
	public static void main(String[] args) {
		
		int [] x = {1,3,2,1,5,5,8,3,2,7};
		List<Integer> y = new ArrayList<>();
		y.add(0, x[0]);
		for (int i : x) {
			/*
			boolean flag = false;
			for (int z = 0; z < y.size(); z++) {
				if(i == y.get(z)) {
					flag = false;
					break;
				} else {
					flag = true;
				}
			}
			
			if(flag) {
				y.add(i);
			}*/
			
			if(!y.contains(i)) {
				y.add(i);
			}
		}
		
		System.out.println(y.toString());
	}
}

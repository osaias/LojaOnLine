package br.com.testes;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import Util.Sessao;
import br.com.cliente.Usuario;
import br.com.compra.Produto;
import simulador.processos.PaginaCadastroUsuario;
import simulador.processos.PaginaCarrinho;
import simulador.processos.PaginaLogin;
import simulador.processos.PaginaPagamento;
import simulador.processos.PaginaPedido;
import simulador.processos.PaginaPrincipal;
import simulador.processos.PaginaProduto;

public class Site {

	public static void main(String[] args) {

		boolean repetir = true;
		
		PaginaPrincipal pagPrinc = new PaginaPrincipal();
		PaginaProduto pagProd = new PaginaProduto();
		PaginaCadastroUsuario pagCadUsu = new PaginaCadastroUsuario();
		PaginaCarrinho pagCarr = new PaginaCarrinho();
		PaginaLogin pagLogin = new PaginaLogin();
		PaginaPedido pagPed = new PaginaPedido();
		PaginaPagamento pagPag = new PaginaPagamento();
		
		do {
			
			Produto produto = pagPrinc.listarProdutos();
			pagPrinc.clicar(produto);
			
			int opcao;
			do {
				
				System.out.print("OPÇÃO: ");
				Scanner entrada = new Scanner(System.in);
				opcao = Integer.parseInt(entrada.nextLine());
			
				switch(opcao) {
				case 1:
					produto = pagPrinc.listarProdutos();
					pagPrinc.clicar(produto);
					break;
					
				case 2:
					//na pg produto
					pagCarr.adicionarNoCarrinho(produto);
					pagCarr.exibirListaProduto();
					break;
					
				case 3:
					System.out.print("C.E.P........: ");
					String cep = entrada.nextLine();
					
					pagProd.exibirProduto(produto);
					System.out.println("                                FRETES ");
					System.out.println("================================================================================");
					
					Sessao.setAtributo("fretes", pagProd.calcularFrete(produto, cep));
					
					Map<String, BigDecimal> fretesProduto = (Map<String, BigDecimal>) Sessao.getAtributo("fretes");
					Set<String> chaves = fretesProduto.keySet();
					for (String chave : chaves)
					{
						if(chave != null)
							System.out.println(String.format("%1$-5s %2$-55s %3$-3s %4$.2f", " =>> ", chave, " R$", fretesProduto.get(chave)));
					}
					System.out.println("================================================================================");
					break;
					
				case 4:
					if (pagLogin.logar()) {
						String pagina = (String) Sessao.getAtributo("paginaAtual");
						
						if(pagina.equals("PaginaProduto")){
							pagProd.exibirProduto(produto);
						}
						
						if (pagina.equals("PaginaCarrinho")) {
							pagCarr.exibirListaProduto();
						}
						
					} else {
							
						throw new IllegalArgumentException("E-mail/senha Inválido!!!");
					}
					break;
					
				case 5:
					if(pagCadUsu.cadastrarUsuario()) {
						System.out.println("Usuario cadastado com sucesso !!!");
						
						String pagina = (String) Sessao.getAtributo("paginaAtual");
						
						if(pagina.equals("PaginaProduto")){
							pagProd.exibirProduto(produto);
						}
						
						if (pagina.equals("PaginaCarrinho")) {
							pagCarr.exibirListaProduto();
						}
					} else {
						System.out.println(" Usuario não cadastrado !!!");

					}
					break;
					
				case 6:
					//adicionar
					produto = pagPrinc.listarProdutos();
					pagPrinc.clicar(produto);
					break;
					
				case 7: 
					//remover
					pagCarr.removerDoCarrinho();
					pagCarr.exibirListaProduto();
					break;
				
				case 8:
					//qtd
					pagCarr.selecionarQuantidade();
					pagCarr.exibirListaProduto();
					break;
					
				case 9:
					//comprar
					Usuario usuario = (Usuario) Sessao.getAtributo("usuario");
					
					if (usuario == null) {
						if (pagLogin.logar()) {
							
							pagPed.efetuarPedido();
						} else {
							if (pagCadUsu.cadastrarUsuario()) {
								
								pagPed.efetuarPedido();
							};
						}
					}
					break;
					
				case 10:
					//boleto
					pagPed.gerarPedido();
					pagPag.gerarBoleto();
					break;
					
				case 11:
					//credito
					
					break;
					
				case 12:
					//debito
					
					break;
					
				}
				
			} while (opcao != 1);
				
		} while (repetir = true);
		
	}

}

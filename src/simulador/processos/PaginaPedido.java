package simulador.processos;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import Util.Console;
import Util.GestorDeServicos;
import Util.Servico;
import Util.ServicoTransporte;
import Util.Sessao;
import br.com.DAO.EnderecoDAO;
import br.com.DAO.EstoqueDAO;
import br.com.DAO.TransportadoraDAO;
import br.com.cliente.Usuario;
import br.com.compra.CarrinhoDeCompra;
import br.com.compra.ItemCarrinho;
import br.com.compra.Pedido;
import br.com.frete.Frete;
import br.com.loja.Loja;
import br.com.transportadora.Transportadora;

public class PaginaPedido {

	Pedido pedido = new Pedido();
	GestorDeServicos gestor = new GestorDeServicos();
	
	public void efetuarPedido() {
		
		Scanner sc = new Scanner(System.in);
		
		Console.limpatela();
		System.out.println("=================================================================================");
		System.out.println("                               PAGINA DO PEDIDO                                  ");
		System.out.println("=================================================================================");
		exibirCarrinho();
		System.out.println("---------------------------------------------------------------------------------");
		exibirValorPedido();
		System.out.println("---------------------------------------------------------------------------------");
		exibirEnderecoEntrega();
		System.out.println("---------------------------------------------------------------------------------");
		exibirFretes();
		System.out.println("---------------------------------------------------------------------------------");
		exibirFormaPgto();
		System.out.println("=================================================================================");
		System.out.print("Escolha o frete: ");
		String frete = sc.nextLine();
		Sessao.setAtributo("fretePedido", frete.toUpperCase());
		System.out.println("Escolha a forma pagamento: ");
	}
	
	public void gerarPedido() {
		
		CarrinhoDeCompra carrinho = (CarrinhoDeCompra) Sessao.getAtributo("carrinho");
		List<ItemCarrinho> produtos = (List<ItemCarrinho>) Sessao.getAtributo("listaDeProdutos");
		Usuario usuario = (Usuario) Sessao.getAtributo("usuario");
		
		Frete frete = (Frete) Sessao.getAtributo("frete");
		Map<String, BigDecimal> fretes = (Map<String, BigDecimal>) Sessao.getAtributo("fretes");
		String freteEscolhido = (String) Sessao.getAtributo("fretePedido");
		
		frete.setServico(freteEscolhido);
		frete.setValorFrete(fretes.get(freteEscolhido));
		
		pedido.setEnderecoEntrega(usuario.getEndereco());
		//pedido.setFormaPgto(formaPgto);
		pedido.setFrete(frete);
		pedido.setLoja(new Loja());
		//pedido.setNumero(numero);
		pedido.setProdutos(carrinho.getProdutos());
		pedido.setTotal(carrinho.getPrecoTotal());
		pedido.setUsuario(usuario);
		
		Sessao.setAtributo("pedido", pedido);
	
	}
	
	private void exibirCarrinho() {
		
		List<ItemCarrinho> produtos = (List<ItemCarrinho>) Sessao.getAtributo("listaDeProdutos");
		
		for (ItemCarrinho produto : produtos) {
			
			System.out.println(String.format("%1$-25s %2$-40s %3$.2f  %4$03d", produto.getProduto().getNome().toUpperCase(),
					produto.getProduto().getDescricao(), produto.getProduto().getPreco(), quantidade(produto)));
		}
	}
	
	private void exibirFretes() {

		System.out.println("\nFRETES: ");
		
		Map<String, BigDecimal> fretes = (Map<String, BigDecimal>) Sessao.getAtributo("fretes");
		
		if (fretes == null) {
		
			fretes = calcularFrete();
			Sessao.setAtributo("fretes", fretes);
		}
		
		Set<String> chaves = fretes.keySet();
		for (String chave : chaves)
		{
			if(chave != null)
				System.out.println(String.format("%1$-5s %2$-55s %3$-3s %4$.2f", " =>> ", chave, " R$", fretes.get(chave)));
		}
	}
	
	private void exibirValorPedido() {
		
		CarrinhoDeCompra carrinho = (CarrinhoDeCompra) Sessao.getAtributo("carrinho");
		
		System.out.println(String.format("%1$-25s %2$.2f","TOTAL: R$", carrinho.getPrecoTotal()));
	}
	
	private void exibirEnderecoEntrega() {
		
		Usuario usuario = (Usuario) Sessao.getAtributo("usuario");
		
		usuario.setEndereco(new EnderecoDAO().consultar(usuario));
		
		Sessao.setAtributo("usuario", usuario);
		
		System.out.println("\nENDEREÇO DE ENTREGA: ");
		System.out.println("            " + usuario.getNome().toUpperCase());
		System.out.print("            " + usuario.getEndereco().getCidade() + " - ");
		System.out.println(usuario.getEndereco().getUf());
		System.out.println("            " + usuario.getEndereco().getCep());
	}
	
	private void exibirFormaPgto() {
		
		System.out.println("FORMAS DE PAGAMENTO: \n");
		System.out.println("          10 - Boleto            11 - Crédito             12 - Débito            ");
	}
	
	private int quantidade(ItemCarrinho item) {
		
		int qtd = 0;
		CarrinhoDeCompra carrinho = (CarrinhoDeCompra) Sessao.getAtributo("carrinho");
		
		Set<ItemCarrinho> itens = carrinho.getItens();
		for (ItemCarrinho it : itens) {
			
			if (it.getProduto().getNome().equals(item.getProduto().getNome())) {
				qtd++;
			}
		}
		
		return qtd;
	}
	
	private Map<String,BigDecimal> calcularFrete() {
		
		Frete frete = new Frete();
		Map<String,BigDecimal> fretes = new HashMap<>();
		
		CarrinhoDeCompra carrinho = (CarrinhoDeCompra) Sessao.getAtributo("carrinho");
		Usuario usuario = (Usuario) Sessao.getAtributo("usuario");
		
		frete.setProdutos(carrinho.getProdutos());
		frete.setCepOrigem(new EstoqueDAO().pegarEndereco().getCep());
		frete.setCepDestino(usuario.getEndereco().getCep());
		
		Sessao.setAtributo("frete", frete);
		
		List<Transportadora> transportadoras = new TransportadoraDAO().getTransportadorasConveniadas();
		for (ServicoTransporte transportadora : transportadoras) {

			if(gestor.conectar(transportadora)) {
				frete.setTransportador(transportadora);
				Map<String, BigDecimal> freteTranportador = gestor.getFreteTranportador(frete);
				
				if (freteTranportador != null) {
					fretes.putAll(freteTranportador);
				}
			}
		}
		
		return fretes;
	}
}

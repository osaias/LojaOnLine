package simulador.processos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import Util.GestorDeServicos;
import Util.Console;
import Util.Servico;
import br.com.DAO.EstoqueDAO;
import br.com.DAO.TransportadoraDAO;
import br.com.cliente.Usuario;
import br.com.compra.Produto;
import br.com.estoque.Estoque;
import br.com.frete.Frete;
import br.com.transportadora.Transportadora;
import simulador.BancoDados.TabelaTransportadoras;


public class PaginaProduto implements Logavel{
	
	PaginaCadastroUsuario linkPagCadUsuario = new PaginaCadastroUsuario();
	GestorDeServicos gestor = new GestorDeServicos();
	Map<String,BigDecimal> fretes = new HashMap<>();
	
	public boolean exibirProduto(Produto produto) {

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
		System.out.println("\n OPÇÃO: ");
		
		Scanner entrada = new Scanner(System.in);
		int opcao = Integer.parseInt(entrada.nextLine());
		
		switch(opcao) {
		case 1:
			return false;
		case 2:
			comprar();
			return true;
		case 3:
			System.out.println("\n\n C.E.P........:");
			String cep = entrada.nextLine();
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("                            FRETES                                              ");
			System.out.println("--------------------------------------------------------------------------------");
			fretes = calcularFrete(produto, cep);
			Set<String> chaves = fretes.keySet();
			for (String chave : chaves)
			{
				if(chave != null)
					System.out.println(chave + " - " + fretes.get(chave));
			}
			return true;
		case 4:
			if(logar()) {
				//recursividade
				this.exibirProduto(produto);
			}
			return true;
		case 5:
			Usuario usuario = cadastrarUsuario();
			if(usuario.isLogado()) {
				//recursividade
				this.exibirProduto(produto);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean logar() {

		return linkPagCadUsuario.logar();
	}

	@Override
	public Usuario cadastrarUsuario() {

		return linkPagCadUsuario.cadastrarUsuario();
	}
	
	private Map<String,BigDecimal> calcularFrete(Produto produto, String cep) {
		
		Frete frete = new Frete();
		Map<String,BigDecimal> fretes = new HashMap<>();
		
		frete.setProdutos(Arrays.asList(produto));
		frete.setCepOrigem(new EstoqueDAO().pegarEndereco().getCep());
		frete.setCepDestino(cep);
		
		List<Transportadora> transportadoras = new TransportadoraDAO().getTransportadorasConveniadas();
		for (Servico transportadora : transportadoras) {
			
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
	
	private void comprar() {
		
	}
	
	private void exibirFormasPgto(Produto produto) {
		System.out.println("\n\n");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println(" À VISTA............: R$ " + String.format("%1$.2f", (produto.getPreco() - produto.getPreco() * 0.1)));
		System.out.println(" PARCELADO..(12x )..: R$ " + String.format("%1$.2f",(produto.getPreco() / 12)));
		System.out.println("--------------------------------------------------------------------------------");
	}


	
	

	
/*	public static void main(String[] args) {
		
		PaginaProduto p  = new PaginaProduto();
		Produto produto = new Produto("Tv Sony","","", 2000.00, 100.0, 10.0, 150.00, 3250.00);
		p.calcularFrete(produto, "68915-100");
		//p.exibirProduto(produto)

	}*/
}

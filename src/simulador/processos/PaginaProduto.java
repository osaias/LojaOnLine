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
import Util.ServicoTransporte;
import Util.Sessao;
import br.com.DAO.EstoqueDAO;
import br.com.DAO.TransportadoraDAO;
import br.com.cliente.Usuario;
import br.com.compra.Produto;
import br.com.estoque.Estoque;
import br.com.frete.Frete;
import br.com.transportadora.Transportadora;
import simulador.BancoDados.TabelaTransportadoras;


public class PaginaProduto{
	
	PaginaCadastroUsuario linkPagCadUsuario = new PaginaCadastroUsuario();
	PaginaCarrinho linkPagCarrinho = new PaginaCarrinho();
	GestorDeServicos gestor = new GestorDeServicos();
	Map<String,BigDecimal> fretes = new HashMap<>();
	Usuario usuario = new Usuario();
	
	public void exibirProduto(Produto produto) {
		
		Sessao.setAtributo("paginaAtual", "PaginaProduto");
		
		Usuario usuario = (Usuario) Sessao.getAtributo("usuario");
		
		Console.limpatela();
		System.out.println("=================================================================================");
		System.out.println("                               PAGINA DO PRODUTO                                 ");
		System.out.println("=================================================================================");
		
		if (usuario == null) {
			System.out.println("                                                  4 - LOGIN / 5 - CADASTRE-SE");
		} else {
			System.out.println(String.format("%1$-52s %2$-10s %3$-15s", " ", "Bem Vindo: ", usuario.getNome()));
		}
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("\n");
		System.out.println(String.format("%1$-25s %2$-40s %3$-9s", "NOME", "DESCRIÇAO", "VALOR"));
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println(String.format("%1$-25s %2$-40s %3$.2f", produto.getNome().toUpperCase(), produto.getDescricao(), produto.getPreco()));

		exibirFormasPgto(produto);

		System.out.println("\n");
		System.out.println("             1 - VOLTAR         2 - ADICIONAR AO CARRINHO ");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("                                                         3 - CALCULAR FRETE");
		System.out.println("================================================================================");

	}

	public Map<String,BigDecimal> calcularFrete(Produto produto, String cep) {
		
		Frete frete = new Frete();
		Map<String,BigDecimal> fretes = new HashMap<>();
		
		frete.setProdutos(Arrays.asList(produto));
		frete.setCepOrigem(new EstoqueDAO().pegarEndereco().getCep());
		frete.setCepDestino(cep);
		
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
	
	private void exibirFormasPgto(Produto produto) {
		System.out.println("\n");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println(" À VISTA............: R$ " + String.format("%1$.2f", (produto.getPreco() - produto.getPreco() * 0.1)));
		System.out.println(" PARCELADO..(12x )..: R$ " + String.format("%1$.2f",(produto.getPreco() / 12)));
		System.out.println("--------------------------------------------------------------------------------");
	}
}

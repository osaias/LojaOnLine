package simulador.processos;

import java.util.List;

import Util.Console;
import Util.Sessao;
import br.com.compra.Pedido;
import br.com.compra.Produto;

public class PaginaResultado {
	
	private String resultado;

	public PaginaResultado(String mensagem) {

		this.resultado = mensagem;
	}

	public void exibir() {
		Pedido pedido = (Pedido) Sessao.getAtributo("pedido");
		
		Console.limpatela();
		System.out.println("=================================================================================");
		System.out.println("                                 PEDIDO                       numero: " + pedido.getNumero());
		System.out.println("=================================================================================");
		System.out.println();
		System.out.println(pedido.getLoja().toString());
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("                            DESCRIÇÃO                               VALOR        ");
		System.out.println();
		listarProdutos(pedido);
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("                                                          TOTAL: R$" + pedido.getTotal());
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("Forma de Pagamento: " + pedido.getFormaPgto());
		System.out.println("Frete :" + pedido.getFrete().getServico() + "          Valor R$ " + pedido.getFrete().getValorFrete());
		System.out.println("---------------------------------------------------------------------------------");
		
		if (pedido.getUsuario().getCpf() != null) {
			System.out.print(pedido.getUsuario().getCpf());
			
		} else {
			System.out.print(pedido.getUsuario().getCnpj());
		}
		
		System.out.println(" - " + pedido.getUsuario().getNome() + " " + pedido.getUsuario().getSobrenome());
		System.out.println();
		System.out.println("ENDEREÇO DE ENTREGA :                                                            ");
		System.out.println(pedido.getEnderecoEntrega().toString());
		System.out.println("=================================================================================");
		System.out.printf("%1$58s\n",resultado.toUpperCase());
		System.out.println("=================================================================================");
	}

	private void listarProdutos(Pedido pedido) {

		List<Produto> produtos = pedido.getProdutos();
		
		for (Produto produto : produtos) {
			System.out.printf("%1$-65s %2$8.2f", produto.getNome() + produto.getDescricao(),produto.getPreco());
			System.out.println();
		}
		
	}

	public void exibirErro() {
		
		System.out.println("=================================================================================");
		System.out.println("                               PAGINA DO RESULTADO                               ");
		System.out.println("=================================================================================");
		System.out.println();
		System.out.println(resultado.toUpperCase());
		System.out.println();
		System.out.println("=================================================================================");
	}

}

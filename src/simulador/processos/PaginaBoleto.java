package simulador.processos;

import Boleto.Boleto;
import Util.Sessao;

public class PaginaBoleto {

	public void exibir() {

		Boleto boleto = (Boleto) Sessao.getAtributo("boleto");
		
		System.out.println("=================================================================================");
		System.out.println("                               PAGINA DO BOLETO                                  ");
		System.out.println("=================================================================================");
		System.out.println();
		System.out.printf("%1$-20s %2$5d %3$50s",boleto.getBanco().toUpperCase(), boleto.getNumeroBanco(), boleto.getCodigoBarras());
		System.out.println("=================================================================================");
		System.out.printf("", boleto.getLocalPagamento(), boleto.getVencimento());
		System.out.println("---------------------------------------------------------------------------------");
		System.out.printf("", boleto.getCedente().getNome(), boleto.getAgencia(), boleto.getCodigoCedente());
		System.out.println("---------------------------------------------------------------------------------");
		System.out.printf("", boleto.getDataDocumento(), boleto.getNumeroDocumento(), boleto.getProcessamento(), boleto.getCarteira(), boleto.getNossoNumero());
		System.out.println("---------------------------------------------------------------------------------");
		System.out.printf("", boleto.getCarteira(), boleto.getValor());
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println();
		System.out.printf("", boleto.getInstrucoes(), "");
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println();
		System.out.printf("", boleto.getSacado().getNome(), " - ");
		
		if (boleto.getSacado().getCpf() != null) {
			System.out.printf("", boleto.getSacado().getCpf());
		} else {
			System.out.printf("", boleto.getSacado().getCnpj());
		}
		System.out.printf(" ", boleto.getSacado().getEndereco());
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("=================================================================================");
	}

}

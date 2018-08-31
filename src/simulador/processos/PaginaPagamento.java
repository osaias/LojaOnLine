package simulador.processos;

import java.util.List;

import Util.GestorDeServicos;
import Util.Sessao;
import br.com.BancoFinanceira.Agencia;
import br.com.BancoFinanceira.Convenio;
import br.com.BancoFinanceira.TipoConvenio;
import br.com.DAO.BancoDAO;
import br.com.cliente.Usuario;
import br.com.compra.Pedido;

public class PaginaPagamento {

	GestorDeServicos gestor = new GestorDeServicos();
	
	public boolean gerarBoleto() {

		Usuario usuario = (Usuario) Sessao.getAtributo("usuario");
		Pedido pedido = (Pedido) Sessao.getAtributo("pedido");
		
		/*pedido.setUsuario(usuario);
		pedido.setLoja(new Loja());*/
		//pedido.setFormaPgto(FormaPgto.BOLETO);
		
		List<Agencia> bancosConveniados = new BancoDAO().getBancosConveniadas();
		
		for (Agencia agencia : bancosConveniados) {
			
			List<Convenio> convenios = agencia.getConvenios();
			
			for (Convenio convenio : convenios) {
				
				if (convenio.getTipo().equals(TipoConvenio.BOLETO)) {

					if (gestor.conectar(agencia)) {
						
						 return gestor.gerarBoleto(pedido);
					}
				
					break;
				}
			}
			
		}
		return false;

	}
	/*
	 * implementar o metodo pgto boleto
	 * implementar o metodo pgto debito
	 * implementar o metodo pgto credito
	 * 
	 */
}

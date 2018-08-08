package Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.XStream;

import Boleto.Boleto;
import br.com.BancoFinanceira.Agencia;
import br.com.BancoFinanceira.Financeira;
import br.com.BancoFinanceira.TipoConvenio;
import br.com.DAO.BancoDAO;
import br.com.DAO.FinanceiraDAO;
import br.com.DAO.TransportadoraDAO;
import br.com.compra.Pedido;
import br.com.frete.Frete;
import br.com.pagamento.FormaPgto;
import br.com.transportadora.Transportadora;
import simulador.servicos.externos.ServicosBcoBrasil;
import simulador.servicos.externos.ServicosBraspress;
import simulador.servicos.externos.ServicosCorreio;
import simulador.servicos.externos.ServicosFedex;
import simulador.servicos.externos.ServicosJadlog;
import simulador.servicos.externos.ServicosTotalExpress;

public class GestorDeServicos {
	
	private List<Servico> transportadores = new ArrayList<Servico>();
	private List<Servico> agencias = new ArrayList<Servico>();
	private List<Servico> fornecedores = new ArrayList<Servico>();
	private List<Servico> financeiras = new ArrayList<Servico>();
	
	public GestorDeServicos() {
		
		getTransportadores();
		getAgencias();
		getFornecedores();
		//getFinanceiras();
	}
	


	public boolean conectar(Servico empresa) {

		if (empresa.getInstanciaServico().getClass().equals(ServicosCorreio.class)) {
			return true;
		} else if (empresa.getInstanciaServico().getClass().equals(ServicosBraspress.class)) {
			return true;
		}else if (empresa.getInstanciaServico().getClass().equals(ServicosFedex.class)) {
			return true;
		}else if (empresa.getInstanciaServico().getClass().equals(ServicosJadlog.class)) {
			return true;
		}else if (empresa.getInstanciaServico().getClass().equals(ServicosTotalExpress.class)) {
			return true;
		}else if (empresa.getInstanciaServico().getClass().equals(ServicosBcoBrasil.class)) {
			return true;
		}
		return false;
	}

	public Map<String, BigDecimal> getFreteTranportador(Frete frete) {
		
		ServicoTransporte transportador = frete.getTransportador();
		transportador.calcularFrete(frete);
		//this.transportadores.add(transportador);
		
		return ((Transportadora) transportador).getFretes();
	}
	
	public void gerarBoleto(Pedido pedido) {
		
		if (!pedido.getFormaPgto().equals(FormaPgto.BOLETO)) {
			throw new IllegalArgumentException("Forma de pgto não é compativel com boleto");
		}
		
		Agencia[] agencias = TipoConvenio.BOLETO.getAgencias();
		
		Object instanciaServico = agencias[0].getInstanciaServico();
				
		if (instanciaServico.equals(null)) {
			throw new IllegalArgumentException("Não conseguiu a instancia do servico");
		}
		
		Agencia agencia = agencias[0];
		
		Boleto boleto = agencia.getBoleto(pedido);

//		PedidoDAO pedidoDAO = new PedidoDAO();
//		
//		pedidoDAO.put();
		
	}
	
	



	private void getTransportadores() {
		
		List<Transportadora> transportadorasConveniadas = new TransportadoraDAO().getTransportadorasConveniadas();
		
		for (Transportadora transportadora : transportadorasConveniadas) {
			transportadores.add(transportadora);
		}
	}
	
	private void getFinanceiras() {
		
		List<Financeira> financeirasConveniadas = new FinanceiraDAO().getFinanceiras();
		
		for (Financeira financeira : financeirasConveniadas) {
			financeiras.add(financeira);
		}
	}

	private void getFornecedores() {
		// TODO Auto-generated method stub
		
	}

	private void getAgencias() {
		
		List<Agencia> bancosConveniadas = new BancoDAO().getBancosConveniadas();
		
		for (Agencia agencia : bancosConveniadas) {
			agencias.add(agencia);
		}
	}
	
	
	public static void main(String[] args) {
		GestorDeServicos g = new GestorDeServicos();
		/*Servico s = new CorreiosTransportes(null);
		g.conectar(s);
		Frete f = new Frete();
		f.setTransportador(new CorreiosTransportes(null));
		f.setCepDestino("08577-520");
		f.setCepOrigem("08577-500");
		f.setProdutos(Arrays.asList(new Produto("Tv Sony","","", 2000.00, 100.0, 10.0, 150.00, 3250.00)));
		g.getFreteTranportador(f);*/
		
		/*Pedido ped = new Pedido();
		
		ped.setEnderecoEntrega(enderecoEntrega);
		ped.setFormaPgto(formaPgto);
		ped.setFrete(frete);
		ped.setLoja(loja);
		ped.setNumero(numero);
		ped.setProdutos(produtos);
		ped.setTotal(total);
		ped.setUsuario(usuario);*/
			
	}

}

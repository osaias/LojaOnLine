package Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import br.com.compra.Produto;
import br.com.frete.Frete;
import br.com.transportadora.CorreiosTransportes;
import br.com.transportadora.Transportadora;
import simulador.servicos.externos.ServicosBraspress;
import simulador.servicos.externos.ServicosCorreio;
import simulador.servicos.externos.ServicosFedex;
import simulador.servicos.externos.ServicosJadlog;
import simulador.servicos.externos.ServicosTotalExpress;
import simulador.servicos.externos.ServicosCorreio.Retorno;

public class GestorDeServicos {
	
	private List<Servico> transportadores = new ArrayList<Servico>();

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
		}
		
		return false;
	}

	public Map<String, BigDecimal> getFreteTranportador(Frete frete) {
		
		Servico transportador = frete.getTransportador();
		transportador.calcularFrete(frete);
		this.transportadores.add(transportador);
		
		return ((Transportadora) transportador).getFretes();
	}
	
	
	
	public List<Servico> getTransportadores() {
		return transportadores;
	}

	public static void main(String[] args) {
		GestorDeServicos g = new GestorDeServicos();
		Servico s = new CorreiosTransportes(null);
		g.conectar(s);
		Frete f = new Frete();
		f.setTransportador(new CorreiosTransportes(null));
		f.setCepDestino("08577-520");
		f.setCepOrigem("08577-500");
		f.setProdutos(Arrays.asList(new Produto("Tv Sony","","", 2000.00, 100.0, 10.0, 150.00, 3250.00)));
		g.getFreteTranportador(f);
			
	}

}

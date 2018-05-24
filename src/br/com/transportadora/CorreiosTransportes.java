package br.com.transportadora;


import java.util.Arrays;
import br.com.compra.Produto;
import br.com.frete.Frete;
import simulador.servicos.externos.ServicosCorreio;
import simulador.servicos.externos.ServicosCorreio.Retorno;

public class CorreiosTransportes extends Transportadora{


	public CorreiosTransportes(String nome) {
		super(nome);
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> T getInstanciaServico() {
		ServicosCorreio servico = new ServicosCorreio();
		return (T) servico;
		
	}
	
	@Override
	public void calcularFrete(Frete frete) {
		
		ServicosCorreio servico = getInstanciaServico();
		if (servico.cepAtendido(frete.getCepDestino())) {
			Object retorno = servico.getFrete(frete);
			this.setFretes(((Retorno) retorno).getFretes());
			this.setValorPeso(((Retorno) retorno).getPesoTotal());

		}
	}
	
	public static void main(String[] args) {

			CorreiosTransportes ct = new CorreiosTransportes(null);
		/*	ct.setEstadosAtendidos();
		ct.setRegioesAtendidas();
		List<Regioes> listaDeRegioes = ct.getCoberturaRegional();
		List<Estados> listaDeEstados = ct.getCoberturaEstadual();

		Map<Regioes,List<Estados>> cobertura = new HashMap<>();

		for(Regioes regiao : listaDeRegioes ) {
			List<Estados> lista = new ArrayList<>();

			for (Estados estado: listaDeEstados) {

				if (regiao.equals(estado.getRegiao())) {
					lista.add(estado);
				}
			}
			cobertura.put(regiao, lista);
		}
			for(ServicosCorreio estado : ServicosCorreio.values()) {
				List<Object> l;
				estado.getCeps().forEach(l ,c -> l.add(c.getEstado()));
			}*/

			/*ServicosCorreio sc = ct.getInstanciaServico();
			sc.getServicos();*/
			
			Frete frete = new Frete();
			frete.setTransportador(new CorreiosTransportes(null));
			frete.setCepDestino("68915-100");
			frete.setCepOrigem("07600-100");
			frete.setProdutos(Arrays.asList(new Produto("Tv Sony","","", 2000.00, 100.0, 10.0, 150.00, 3250.00)));
			
			ct.calcularFrete(frete);
			System.out.println();
			
		}


	

}

package simulador.servicos.externos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.compra.Produto;
import br.com.frete.Ceps;
import br.com.frete.Estados;
import br.com.frete.Frete;
import br.com.frete.Regioes;
import br.com.transportadora.CorreiosTransportes;

public class ServicosCorreio{

	public boolean cepAtendido(String cepDestino) {
		int cepPesquisado = Integer.valueOf(cepDestino.replaceAll("-", ""));
		List<Servicos> servicos = getServicos();

		for (Servicos servico : servicos) {
			for (Ceps cep : servico.getCeps() ) {

				int cepInicio = Integer.valueOf(cep.getCepInicio().replaceAll("-",""));
				int cepFim = Integer.valueOf(cep.getCepFim().replaceAll("-", ""));

				if (cepPesquisado >= cepInicio && cepPesquisado <= cepFim) {
					return true;
				}
			}
		}
		return false;
	}

	public List<Estados> getEstadosAtendidos() {

		List<Estados> listaDeEstados = new ArrayList<>();
		List<Servicos> servicos = getServicos();

		for (Servicos servico : servicos) {

			for (Ceps cep : servico.getCeps()) {
				if (!listaDeEstados.contains(cep.getEstado())) {
					listaDeEstados.add(cep.getEstado());
				}
			}
		}

		return listaDeEstados;
	}


	public List<Regioes> getRegioesAtendidas() {

		List<Regioes> listaDeRegioes = new ArrayList<>();
		List<Estados> listaDeEstados = this.getEstadosAtendidos();

		for(Estados estado : listaDeEstados ) {

			if (!listaDeRegioes.contains(estado.getRegiao())) {
				listaDeRegioes.add(estado.getRegiao());
			}
		}
		return listaDeRegioes;

	}

	public Map<Regioes, List<Estados>> getCobertura() {

		Map<Regioes,List<Estados>> cobertura = new HashMap<>();
		List<Regioes> listaDeRegioes = this.getRegioesAtendidas();
		List<Estados> listaDeEstados = this.getEstadosAtendidos();

		for(Regioes regiao : listaDeRegioes ) {
			List<Estados> lista = new ArrayList<>();

			for (Estados estado: listaDeEstados) {

				if (regiao.equals(estado.getRegiao())) {
					lista.add(estado);
				}
			}
			cobertura.put(regiao, lista);
		}
		return cobertura;
	}

	public List<Servicos> getServicos() {

		Servicos[] servicos = Servicos.values();

		List<Servicos> lista = new ArrayList<>();

		for(Servicos nome : servicos) {
			lista.add(nome);
		}

		return lista;
	}

	private double getValorPeso(List<Produto> produtos) {

		double pesoTotal = 0;
		double valorKg = 0.2;
		
		for (Produto p : produtos) {
			pesoTotal += p.getPeso();
		}
		
		return pesoTotal * valorKg / 100;
		
	}

	private double getPeso(List<Produto> produtos) {

		double pesoTotal = 0;
		
		for (Produto p : produtos) {
			pesoTotal += p.getPeso();
		}
		
		return pesoTotal;
		
	}
	
	private double getValorDistancia(Frete frete) {
		int cepOrigem = Integer.valueOf(frete.getCepOrigem().replaceAll("-",""));
		int cepDestino = Integer.valueOf(frete.getCepDestino().replaceAll("-", ""));
		Ceps origem = null;
		Ceps destino = null;

		List<Servicos> servicos = getServicos();
		for (Servicos servico : servicos ) {

			for (Ceps cep : servico.getCeps()) {

				int cepInicio = Integer.valueOf(cep.getCepInicio().replaceAll("-",""));
				int cepFim = Integer.valueOf(cep.getCepFim().replaceAll("-", ""));

				if (cepOrigem >= cepInicio && cepOrigem <= cepFim) {
					origem = cep;
				}

				if (cepDestino >= cepInicio && cepDestino <= cepFim) {
					destino = cep;
				}
			}
		}
		
		if(origem.equals(destino)) {
			return 0.0;
		}

		if(origem.getEstado().equals(destino.getEstado())) {
			return 2.0;
		}

		if(origem.getEstado().getRegiao().equals(destino.getEstado().getRegiao())) {
			return 3.0;
		}

		return 5.0;
	}

	private List<Servicos> getServicosPorCep(String cepDestino){
		
		int cepPesquisado = Integer.valueOf(cepDestino.replaceAll("-", ""));
		List<Servicos> servicos = getServicos();
		List<Servicos> lista = new ArrayList<>();
		
		for (Servicos servico : servicos) {
			//armazena o menor e o maior cep de cada serviço
			int menorCepServico = Integer.valueOf(servico.getCeps().get(0).getCepInicio().replaceAll("-", ""));
			int maiorCepServico = Integer.valueOf(servico.getCeps().get(0).getCepFim().replaceAll("-", ""));
			boolean servicoAtende = false;
			
			for (Ceps cep : servico.getCeps() ) {
				//armazena cada cep da lista deste servico
				int cepInicioAtual = Integer.valueOf(cep.getCepInicio().replaceAll("-", ""));
				int cepFimAtual = Integer.valueOf(cep.getCepFim().replaceAll("-", ""));
				
				if(cepPesquisado >= cepInicioAtual && cepPesquisado <= cepFimAtual ) {
					servicoAtende = true;
				}
			}
			
			if (servicoAtende) {
				lista.add(servico);
			}
		}
		return lista;
	}
	
	private Map<String,BigDecimal> getValorFrete(Frete frete) {

		double valorPeso = getValorPeso(frete.getProdutos());
		double valorDistancia = getValorDistancia(frete);
		List<Servicos> servicos =  getServicosPorCep(frete.getCepDestino());
		Map<String,BigDecimal> fretes = new HashMap<>();
		
		for (Servicos servico : servicos) {
			BigDecimal valorFrete = new BigDecimal(servico.getTarifa() + valorDistancia + valorPeso)
					.setScale(2, RoundingMode.HALF_EVEN);
			
			fretes.put(servico.name(), valorFrete);
		}

		return fretes;
	}

	public Retorno getFrete(Frete frete) {
		
		double peso = getPeso(frete.getProdutos());
		Map<String,BigDecimal> fretes = getValorFrete(frete);
		
		return new Retorno(peso, fretes);
	
	}
	
	public class Retorno{
		double pesoTotal;
		Map<String,BigDecimal> fretes;
		
		public Retorno(double peso, Map<String,BigDecimal> fretes) {
			this.pesoTotal = peso;
			this.fretes = fretes;
		}
		
		public double getPesoTotal() {
			return this.pesoTotal;
		}

		public Map<String, BigDecimal> getFretes() {
			return fretes;
		}
		
	}
	
	public enum Servicos{

		SEDEX_SEM_CONTRATO(40010, "SEDEX", 10.00,
				Arrays.asList(Ceps.SP_CAPITAL, Ceps.SP_GRANDESP, Ceps.RJ_CAPITAL,Ceps.RJ_GRANDERIO,
						Ceps.ES_CAPITAL,Ceps.MG_GRANDEBH, Ceps.MG_CAPITAL,Ceps.SE_CAPITAL,
						Ceps.BA_CAPITAL, Ceps.BA_GRANDESALVADOR, Ceps.PE_CAPITAL, Ceps.PE_GRANDERECIFE, 
						Ceps.AL_CAPITAL, Ceps.PB_CAPITAL, Ceps.CE_GRANDEFORTALEZA, Ceps.CE_CAPITAL, Ceps.RN_CAPITAL, 
						Ceps.PI_CAPITAL, Ceps.MA_CAPITAL, Ceps.SC_CAPITAL, Ceps.RS_CAPITAL, Ceps.PR_CAPITAL)),

		SEDEX_COBRAR_SEM_CONTRATO(40045, "SEDEX a Cobrar", 5.00,
				Arrays.asList(Ceps.SP_CAPITAL, Ceps.SP_GRANDESP, Ceps.SP_LITORAL, Ceps.SP_INTERIOR, Ceps.RJ_CAPITAL,
						Ceps.RJ_GRANDERIO, Ceps.RJ_INTERIOR, Ceps.ES_CAPITAL, Ceps.ES_INTERIOR, Ceps.MG_CAPITAL, Ceps.MG_GRANDEBH,
						Ceps.MG_INTERIOR, Ceps.BA_CAPITAL, Ceps.BA_GRANDESALVADOR, Ceps.BA_INTERIOR, Ceps.SE_CAPITAL, Ceps.SE_INTERIOR,
						Ceps.PE_CAPITAL, Ceps.PE_GRANDERECIFE, Ceps.PE_INTERIOR, Ceps.AL_CAPITAL, Ceps.AL_INTERIOR, Ceps.PB_CAPITAL,
						Ceps.PB_INTERIOR, Ceps.RN_CAPITAL, Ceps.RN_INTERIOR, Ceps.CE_CAPITAL, Ceps.CE_GRANDEFORTALEZA, Ceps.CE_INTERIOR,
						Ceps.PI_CAPITAL, Ceps.PI_INTERIOR, Ceps.MA_CAPITAL, Ceps.MA_INTERIOR, Ceps.PA_CAPITAL, Ceps.PA_GRANDEBELEM,
						Ceps.PA_INTERIOR, Ceps.AP_CAPITAL, Ceps.AP_INTERIOR, Ceps.AM_CAPITAL, Ceps.AM_INTERIOR, Ceps.RR_CAPITAL,
						Ceps.RR_INTERIOR, Ceps.AC_CAPITAL, Ceps.AC_INTERIOR, Ceps.DF_CAPITAL, Ceps.DF_SATELITE, Ceps.GO_CAPITAL,
						Ceps.GO_GRANDEGOIANIA, Ceps.GO_INTERIOR, Ceps.TO_CAPITAL, Ceps.TO_INTERIOR, Ceps.MT_CAPITAL, Ceps.MT_INTERIOR,
						Ceps.RO_CAPITAL, Ceps.RO_INTERIOR, Ceps.MS_CAPITAL, Ceps.MS_INTERIOR, Ceps.PR_CAPITAL, Ceps.PR_GRANDECURITIBA,
						Ceps.PR_INTERIOR, Ceps.SC_CAPITAL, Ceps.SC_GRANDEFLORIANOPOLIS, Ceps.SC_INTERIOR, Ceps.RS_CAPITAL, Ceps.RS_GRANDEPORTOALEGRE,
						Ceps.RS_INTERIOR)), 

		SEDEX_COBRAR_COM_CONTRATO(40126, "SEDEX a Cobrar", 6.00, 
				Arrays.asList(Ceps.SP_CAPITAL, Ceps.SP_GRANDESP, Ceps.SP_LITORAL, Ceps.SP_INTERIOR, Ceps.RJ_CAPITAL,
						Ceps.RJ_GRANDERIO, Ceps.RJ_INTERIOR, Ceps.ES_CAPITAL, Ceps.ES_INTERIOR, Ceps.MG_CAPITAL, Ceps.MG_GRANDEBH,
						Ceps.MG_INTERIOR, Ceps.BA_CAPITAL, Ceps.BA_GRANDESALVADOR, Ceps.BA_INTERIOR, Ceps.SE_CAPITAL, Ceps.SE_INTERIOR,
						Ceps.PE_CAPITAL, Ceps.PE_GRANDERECIFE, Ceps.PE_INTERIOR, Ceps.AL_CAPITAL, Ceps.AL_INTERIOR, Ceps.PB_CAPITAL,
						Ceps.PB_INTERIOR, Ceps.RN_CAPITAL, Ceps.RN_INTERIOR, Ceps.CE_CAPITAL, Ceps.CE_GRANDEFORTALEZA, Ceps.CE_INTERIOR,
						Ceps.PI_CAPITAL, Ceps.PI_INTERIOR, Ceps.MA_CAPITAL, Ceps.MA_INTERIOR, Ceps.PA_CAPITAL, Ceps.PA_GRANDEBELEM,
						Ceps.PA_INTERIOR, Ceps.AP_CAPITAL, Ceps.AP_INTERIOR, Ceps.AM_CAPITAL, Ceps.AM_INTERIOR, Ceps.RR_CAPITAL,
						Ceps.RR_INTERIOR, Ceps.AC_CAPITAL, Ceps.AC_INTERIOR, Ceps.DF_CAPITAL, Ceps.DF_SATELITE, Ceps.GO_CAPITAL,
						Ceps.GO_GRANDEGOIANIA, Ceps.GO_INTERIOR, Ceps.TO_CAPITAL, Ceps.TO_INTERIOR, Ceps.MT_CAPITAL, Ceps.MT_INTERIOR,
						Ceps.RO_CAPITAL, Ceps.RO_INTERIOR, Ceps.MS_CAPITAL, Ceps.MS_INTERIOR, Ceps.PR_CAPITAL, Ceps.PR_GRANDECURITIBA,
						Ceps.PR_INTERIOR, Ceps.SC_CAPITAL, Ceps.SC_GRANDEFLORIANOPOLIS, Ceps.SC_INTERIOR, Ceps.RS_CAPITAL, Ceps.RS_GRANDEPORTOALEGRE,
						Ceps.RS_INTERIOR)), 

		SEDEX10_SEM_CONTRATO(40215, "SEDEX 10", 12.00,
				Arrays.asList(Ceps.SP_CAPITAL, Ceps.SP_GRANDESP, Ceps.SP_LITORAL, Ceps.SP_INTERIOR, Ceps.RJ_CAPITAL,
						Ceps.RJ_GRANDERIO, Ceps.RJ_INTERIOR, Ceps.ES_CAPITAL, Ceps.ES_INTERIOR, Ceps.MG_CAPITAL, Ceps.MG_GRANDEBH,
						Ceps.MG_INTERIOR, Ceps.BA_CAPITAL, Ceps.BA_GRANDESALVADOR, Ceps.BA_INTERIOR, Ceps.SE_CAPITAL, Ceps.SE_INTERIOR,
						Ceps.PE_CAPITAL, Ceps.PE_GRANDERECIFE, Ceps.PE_INTERIOR, Ceps.AL_CAPITAL, Ceps.AL_INTERIOR, Ceps.PB_CAPITAL,
						Ceps.PB_INTERIOR, Ceps.RN_CAPITAL, Ceps.RN_INTERIOR, Ceps.CE_CAPITAL, Ceps.CE_GRANDEFORTALEZA, Ceps.CE_INTERIOR,
						Ceps.PI_CAPITAL, Ceps.PI_INTERIOR, Ceps.MA_CAPITAL, Ceps.MA_INTERIOR, Ceps.PA_CAPITAL, Ceps.PA_GRANDEBELEM,
						Ceps.PA_INTERIOR, Ceps.AP_CAPITAL, Ceps.AP_INTERIOR, Ceps.AM_CAPITAL, Ceps.AM_INTERIOR, Ceps.RR_CAPITAL,
						Ceps.RR_INTERIOR, Ceps.AC_CAPITAL, Ceps.AC_INTERIOR, Ceps.DF_CAPITAL, Ceps.DF_SATELITE, Ceps.GO_CAPITAL,
						Ceps.GO_GRANDEGOIANIA, Ceps.GO_INTERIOR, Ceps.TO_CAPITAL, Ceps.TO_INTERIOR, Ceps.MT_CAPITAL, Ceps.MT_INTERIOR,
						Ceps.RO_CAPITAL, Ceps.RO_INTERIOR, Ceps.MS_CAPITAL, Ceps.MS_INTERIOR, Ceps.PR_CAPITAL, Ceps.PR_GRANDECURITIBA,
						Ceps.PR_INTERIOR, Ceps.SC_CAPITAL, Ceps.SC_GRANDEFLORIANOPOLIS, Ceps.SC_INTERIOR, Ceps.RS_CAPITAL, Ceps.RS_GRANDEPORTOALEGRE,
						Ceps.RS_INTERIOR)), 

		SEDEX_COM_CONTRATO(40096, "SEDEX", 7.00,
				Arrays.asList(Ceps.SP_CAPITAL, Ceps.SP_GRANDESP, Ceps.SP_LITORAL, Ceps.SP_INTERIOR, Ceps.RJ_CAPITAL,
						Ceps.RJ_GRANDERIO, Ceps.RJ_INTERIOR, Ceps.ES_CAPITAL, Ceps.ES_INTERIOR, Ceps.MG_CAPITAL, Ceps.MG_GRANDEBH,
						Ceps.MG_INTERIOR, Ceps.BA_CAPITAL, Ceps.BA_GRANDESALVADOR, Ceps.BA_INTERIOR, Ceps.SE_CAPITAL, Ceps.SE_INTERIOR,
						Ceps.PE_CAPITAL, Ceps.PE_GRANDERECIFE, Ceps.PE_INTERIOR, Ceps.AL_CAPITAL, Ceps.AL_INTERIOR, Ceps.PB_CAPITAL,
						Ceps.PB_INTERIOR, Ceps.RN_CAPITAL, Ceps.RN_INTERIOR, Ceps.CE_CAPITAL, Ceps.CE_GRANDEFORTALEZA, Ceps.CE_INTERIOR,
						Ceps.PI_CAPITAL, Ceps.PI_INTERIOR, Ceps.MA_CAPITAL, Ceps.MA_INTERIOR, Ceps.PA_CAPITAL, Ceps.PA_GRANDEBELEM,
						Ceps.PA_INTERIOR, Ceps.AP_CAPITAL, Ceps.AP_INTERIOR, Ceps.AM_CAPITAL, Ceps.AM_INTERIOR, Ceps.RR_CAPITAL,
						Ceps.RR_INTERIOR, Ceps.AC_CAPITAL, Ceps.AC_INTERIOR, Ceps.DF_CAPITAL, Ceps.DF_SATELITE, Ceps.GO_CAPITAL,
						Ceps.GO_GRANDEGOIANIA, Ceps.GO_INTERIOR, Ceps.TO_CAPITAL, Ceps.TO_INTERIOR, Ceps.MT_CAPITAL, Ceps.MT_INTERIOR,
						Ceps.RO_CAPITAL, Ceps.RO_INTERIOR, Ceps.MS_CAPITAL, Ceps.MS_INTERIOR, Ceps.PR_CAPITAL, Ceps.PR_GRANDECURITIBA,
						Ceps.PR_INTERIOR, Ceps.SC_CAPITAL, Ceps.SC_GRANDEFLORIANOPOLIS, Ceps.SC_INTERIOR, Ceps.RS_CAPITAL, Ceps.RS_GRANDEPORTOALEGRE,
						Ceps.RS_INTERIOR)), 

		PAC_SEM_CONTRATO(41106, "PAC", 1.50,
				Arrays.asList(Ceps.SP_CAPITAL, Ceps.SP_GRANDESP, Ceps.SP_LITORAL, Ceps.SP_INTERIOR, Ceps.RJ_CAPITAL,
						Ceps.RJ_GRANDERIO, Ceps.RJ_INTERIOR, Ceps.ES_CAPITAL, Ceps.ES_INTERIOR, Ceps.MG_CAPITAL, Ceps.MG_GRANDEBH,
						Ceps.MG_INTERIOR, Ceps.BA_CAPITAL, Ceps.BA_GRANDESALVADOR, Ceps.BA_INTERIOR, Ceps.SE_CAPITAL, Ceps.SE_INTERIOR,
						Ceps.PE_CAPITAL, Ceps.PE_GRANDERECIFE, Ceps.PE_INTERIOR, Ceps.AL_CAPITAL, Ceps.AL_INTERIOR, Ceps.PB_CAPITAL,
						Ceps.PB_INTERIOR, Ceps.RN_CAPITAL, Ceps.RN_INTERIOR, Ceps.CE_CAPITAL, Ceps.CE_GRANDEFORTALEZA, Ceps.CE_INTERIOR,
						Ceps.PI_CAPITAL, Ceps.PI_INTERIOR, Ceps.MA_CAPITAL, Ceps.MA_INTERIOR, Ceps.PA_CAPITAL, Ceps.PA_GRANDEBELEM,
						Ceps.PA_INTERIOR, Ceps.AP_CAPITAL, Ceps.AP_INTERIOR, Ceps.AM_CAPITAL, Ceps.AM_INTERIOR, Ceps.RR_CAPITAL,
						Ceps.RR_INTERIOR, Ceps.AC_CAPITAL, Ceps.AC_INTERIOR, Ceps.DF_CAPITAL, Ceps.DF_SATELITE, Ceps.GO_CAPITAL,
						Ceps.GO_GRANDEGOIANIA, Ceps.GO_INTERIOR, Ceps.TO_CAPITAL, Ceps.TO_INTERIOR, Ceps.MT_CAPITAL, Ceps.MT_INTERIOR,
						Ceps.RO_CAPITAL, Ceps.RO_INTERIOR, Ceps.MS_CAPITAL, Ceps.MS_INTERIOR, Ceps.PR_CAPITAL, Ceps.PR_GRANDECURITIBA,
						Ceps.PR_INTERIOR, Ceps.SC_CAPITAL, Ceps.SC_GRANDEFLORIANOPOLIS, Ceps.SC_INTERIOR, Ceps.RS_CAPITAL, Ceps.RS_GRANDEPORTOALEGRE,
						Ceps.RS_INTERIOR)), 

		PAC_COM_CONTRATO(41068, "PAC", 0.70,
				Arrays.asList(Ceps.SP_CAPITAL, Ceps.SP_GRANDESP, Ceps.SP_LITORAL, Ceps.SP_INTERIOR, Ceps.RJ_CAPITAL,
						Ceps.RJ_GRANDERIO, Ceps.RJ_INTERIOR, Ceps.ES_CAPITAL, Ceps.ES_INTERIOR, Ceps.MG_CAPITAL, Ceps.MG_GRANDEBH,
						Ceps.MG_INTERIOR, Ceps.BA_CAPITAL, Ceps.BA_GRANDESALVADOR, Ceps.BA_INTERIOR, Ceps.SE_CAPITAL, Ceps.SE_INTERIOR,
						Ceps.PE_CAPITAL, Ceps.PE_GRANDERECIFE, Ceps.PE_INTERIOR, Ceps.AL_CAPITAL, Ceps.AL_INTERIOR, Ceps.PB_CAPITAL,
						Ceps.PB_INTERIOR, Ceps.RN_CAPITAL, Ceps.RN_INTERIOR, Ceps.CE_CAPITAL, Ceps.CE_GRANDEFORTALEZA, Ceps.CE_INTERIOR,
						Ceps.PI_CAPITAL, Ceps.PI_INTERIOR, Ceps.MA_CAPITAL, Ceps.MA_INTERIOR, Ceps.PA_CAPITAL, Ceps.PA_GRANDEBELEM,
						Ceps.PA_INTERIOR, Ceps.AP_CAPITAL, Ceps.AP_INTERIOR, Ceps.AM_CAPITAL, Ceps.AM_INTERIOR, Ceps.RR_CAPITAL,
						Ceps.RR_INTERIOR, Ceps.AC_CAPITAL, Ceps.AC_INTERIOR, Ceps.DF_CAPITAL, Ceps.DF_SATELITE, Ceps.GO_CAPITAL,
						Ceps.GO_GRANDEGOIANIA, Ceps.GO_INTERIOR, Ceps.TO_CAPITAL, Ceps.TO_INTERIOR, Ceps.MT_CAPITAL, Ceps.MT_INTERIOR,
						Ceps.RO_CAPITAL, Ceps.RO_INTERIOR, Ceps.MS_CAPITAL, Ceps.MS_INTERIOR, Ceps.PR_CAPITAL, Ceps.PR_GRANDECURITIBA,
						Ceps.PR_INTERIOR, Ceps.SC_CAPITAL, Ceps.SC_GRANDEFLORIANOPOLIS, Ceps.SC_INTERIOR, Ceps.RS_CAPITAL, Ceps.RS_GRANDEPORTOALEGRE,
						Ceps.RS_INTERIOR)), 

		ESEDEX_COM_CONTRATO(81019, "e-SEDEX", 0.85, 
				Arrays.asList(Ceps.SP_CAPITAL, Ceps.SP_GRANDESP, Ceps.RJ_CAPITAL,Ceps.RJ_GRANDERIO,
						Ceps.ES_CAPITAL,Ceps.MG_GRANDEBH, Ceps.MG_CAPITAL,Ceps.SE_CAPITAL,
						Ceps.BA_CAPITAL, Ceps.BA_GRANDESALVADOR, Ceps.PE_CAPITAL, Ceps.PE_GRANDERECIFE, 
						Ceps.AL_CAPITAL, Ceps.PB_CAPITAL, Ceps.CE_GRANDEFORTALEZA, Ceps.CE_CAPITAL, Ceps.RN_CAPITAL, 
						Ceps.PI_CAPITAL, Ceps.MA_CAPITAL, Ceps.SC_CAPITAL, Ceps.RS_CAPITAL, Ceps.PR_CAPITAL)), 

		ESEDEX_PRIORITARIO_COM_CONTRATO(81027, "e-SEDEX Prioritário", 12.00,
				Arrays.asList(Ceps.SP_CAPITAL, Ceps.SP_GRANDESP, Ceps.RJ_CAPITAL,Ceps.RJ_GRANDERIO,
						Ceps.ES_CAPITAL,Ceps.MG_GRANDEBH, Ceps.MG_CAPITAL,Ceps.SE_CAPITAL,
						Ceps.BA_CAPITAL, Ceps.BA_GRANDESALVADOR, Ceps.PE_CAPITAL, Ceps.PE_GRANDERECIFE, 
						Ceps.AL_CAPITAL, Ceps.PB_CAPITAL, Ceps.CE_GRANDEFORTALEZA, Ceps.CE_CAPITAL, Ceps.RN_CAPITAL, 
						Ceps.PI_CAPITAL, Ceps.MA_CAPITAL, Ceps.SC_CAPITAL, Ceps.RS_CAPITAL, Ceps.PR_CAPITAL)), 

		ESEDEX_EXPRESS_COM_CONTRATO(81035, "e-SEDEX Express", 15.00,
				Arrays.asList(Ceps.SP_CAPITAL, Ceps.SP_GRANDESP, Ceps.RJ_CAPITAL,Ceps.RJ_GRANDERIO,
						Ceps.ES_CAPITAL,Ceps.MG_GRANDEBH, Ceps.MG_CAPITAL,Ceps.SE_CAPITAL,
						Ceps.BA_CAPITAL, Ceps.BA_GRANDESALVADOR, Ceps.PE_CAPITAL, Ceps.PE_GRANDERECIFE, 
						Ceps.AL_CAPITAL, Ceps.PB_CAPITAL, Ceps.CE_GRANDEFORTALEZA, Ceps.CE_CAPITAL, Ceps.RN_CAPITAL, 
						Ceps.PI_CAPITAL, Ceps.MA_CAPITAL, Ceps.SC_CAPITAL, Ceps.RS_CAPITAL, Ceps.PR_CAPITAL));

		private int codigo;
		private String nome;
		private double tarifa;
		private List<Ceps> ceps;

		Servicos(int codigo, String nome, double valor, List<Ceps> ceps){
			this.codigo = codigo;
			this.nome = nome;
			this.tarifa = valor;
			this.ceps = ceps;
		}

		public int getCodigo() {
			return codigo;
		}

		public String getNome() {
			return nome;
		}

		public double getTarifa() {
			return tarifa;
		}

		public List<Ceps> getCeps() {
			return ceps;
		}
	}


	public static void main(String[] args) {
		ServicosCorreio sc = new ServicosCorreio();

		/*List<Estados> lista = sc.getEstadosAtendidos();
		List<Servicos> lista = sc.getServicosPorCep("68915-100");
		lista.forEach(i -> System.out.println(i));

		System.out.println(sc.cepAtendido("08577-520"));*/
		//System.out.println(new BigDecimal(2.00 * 10.00 * 0.553).setScale(2, RoundingMode.HALF_EVEN));
		
		Frete frete = new Frete();
		frete.setTransportador(new CorreiosTransportes(null));
		frete.setCepDestino("68915-100");
		frete.setCepOrigem("07600-100");
		frete.setProdutos(Arrays.asList(new Produto("Tv Sony","","", 2000.00, 100.0, 10.0, 150.00, 3250.00)));
		
		Retorno serv =  sc.getFrete(frete);
		System.out.println(serv);
		
	}
}


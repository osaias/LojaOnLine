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

public class ServicosJadlog {

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
		double valorKg = 0.25;

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
			return 0.8;
		}

		if(origem.getEstado().equals(destino.getEstado())) {
			return 2.2;
		}

		if(origem.getEstado().getRegiao().equals(destino.getEstado().getRegiao())) {
			return 3.3;
		}

		return 5.5;
	}

	private List<Servicos> getServicosPorCep(String cepDestino){

		int cepPesquisado = Integer.valueOf(cepDestino.replaceAll("-", ""));
		List<Servicos> servicos = getServicos();
		List<Servicos> lista = new ArrayList<>();

		for (Servicos servico : servicos) {
			//armazena o menor e o maior cep de cada servi�o
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
		JADLOG_DOMESTICO(60010, "domestico", 5.00,
				Arrays.asList(Ceps.BA_CAPITAL, Ceps.BA_GRANDESALVADOR, Ceps.BA_INTERIOR, Ceps.SE_CAPITAL, Ceps.SE_INTERIOR,
						Ceps.PE_CAPITAL, Ceps.PE_GRANDERECIFE, Ceps.PE_INTERIOR, Ceps.AL_CAPITAL, Ceps.AL_INTERIOR, Ceps.PB_CAPITAL,
						Ceps.PB_INTERIOR, Ceps.RN_CAPITAL, Ceps.RN_INTERIOR, Ceps.CE_CAPITAL, Ceps.CE_GRANDEFORTALEZA, Ceps.CE_INTERIOR,
						Ceps.PI_CAPITAL, Ceps.PI_INTERIOR, Ceps.MA_CAPITAL, Ceps.MA_INTERIOR, Ceps.PA_CAPITAL, Ceps.PA_GRANDEBELEM,
						Ceps.PA_INTERIOR, Ceps.AP_CAPITAL, Ceps.AP_INTERIOR, Ceps.AM_CAPITAL, Ceps.AM_INTERIOR, Ceps.RR_CAPITAL,
						Ceps.RR_INTERIOR, Ceps.AC_CAPITAL, Ceps.AC_INTERIOR, Ceps.DF_CAPITAL, Ceps.DF_SATELITE, Ceps.GO_CAPITAL,
						Ceps.GO_GRANDEGOIANIA, Ceps.GO_INTERIOR, Ceps.TO_CAPITAL, Ceps.TO_INTERIOR, Ceps.MT_CAPITAL, Ceps.MT_INTERIOR,
						Ceps.RO_CAPITAL, Ceps.RO_INTERIOR, Ceps.MS_CAPITAL, Ceps.MS_INTERIOR)),

		JADLOG_CORPORATIVO(610045, "corporativo", 10.00,
				Arrays.asList(Ceps.BA_CAPITAL, Ceps.BA_GRANDESALVADOR, Ceps.BA_INTERIOR, Ceps.SE_CAPITAL, Ceps.SE_INTERIOR,
						Ceps.PE_CAPITAL, Ceps.PE_GRANDERECIFE, Ceps.PE_INTERIOR, Ceps.AL_CAPITAL, Ceps.AL_INTERIOR, Ceps.PB_CAPITAL,
						Ceps.PB_INTERIOR, Ceps.RN_CAPITAL, Ceps.RN_INTERIOR, Ceps.CE_CAPITAL, Ceps.CE_GRANDEFORTALEZA, Ceps.CE_INTERIOR,
						Ceps.PI_CAPITAL, Ceps.PI_INTERIOR, Ceps.MA_CAPITAL, Ceps.MA_INTERIOR, Ceps.PA_CAPITAL, Ceps.PA_GRANDEBELEM,
						Ceps.PA_INTERIOR, Ceps.AP_CAPITAL, Ceps.AP_INTERIOR, Ceps.AM_CAPITAL, Ceps.AM_INTERIOR, Ceps.RR_CAPITAL,
						Ceps.RR_INTERIOR, Ceps.AC_CAPITAL, Ceps.AC_INTERIOR, Ceps.DF_CAPITAL, Ceps.DF_SATELITE, Ceps.GO_CAPITAL,
						Ceps.GO_GRANDEGOIANIA, Ceps.GO_INTERIOR, Ceps.TO_CAPITAL, Ceps.TO_INTERIOR, Ceps.MT_CAPITAL, Ceps.MT_INTERIOR,
						Ceps.RO_CAPITAL, Ceps.RO_INTERIOR, Ceps.MS_CAPITAL, Ceps.MS_INTERIOR)), 

		JADLOG_FLUVIAL(610126, "fluvial", 9.00,
				Arrays.asList(Ceps.SE_CAPITAL, Ceps.SE_INTERIOR, Ceps.PE_CAPITAL, Ceps.PE_GRANDERECIFE, Ceps.AL_CAPITAL, 
						Ceps.PB_CAPITAL, Ceps.RN_CAPITAL, Ceps.CE_CAPITAL, Ceps.CE_GRANDEFORTALEZA, Ceps.PI_CAPITAL, Ceps.PI_INTERIOR, 
						Ceps.MA_CAPITAL, Ceps.MA_INTERIOR, Ceps.PA_CAPITAL, Ceps.PA_GRANDEBELEM, Ceps.PA_INTERIOR, Ceps.AP_CAPITAL, 
						Ceps.AP_INTERIOR, Ceps.AM_CAPITAL, Ceps.AM_INTERIOR, Ceps.RR_CAPITAL, Ceps.RR_INTERIOR, Ceps.AC_CAPITAL, 
						Ceps.AC_INTERIOR, Ceps.TO_CAPITAL, Ceps.TO_INTERIOR, Ceps.RO_CAPITAL, Ceps.RO_INTERIOR)), 

		JADLOG_AEREO(60215, "aereo", 12.00, 
				Arrays.asList(Ceps.SE_CAPITAL, Ceps.PE_CAPITAL, Ceps.AL_CAPITAL, Ceps.PB_CAPITAL, Ceps.RN_CAPITAL, Ceps.CE_CAPITAL,
						Ceps.PI_CAPITAL, Ceps.MA_CAPITAL, Ceps.PA_CAPITAL, Ceps.AP_CAPITAL, Ceps.AM_CAPITAL, Ceps.RR_CAPITAL, 
						Ceps.AC_CAPITAL, Ceps.TO_CAPITAL,  Ceps.RO_CAPITAL));

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
}


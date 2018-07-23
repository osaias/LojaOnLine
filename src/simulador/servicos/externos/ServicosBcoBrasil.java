package simulador.servicos.externos;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;

import br.com.BancoFinanceira.Agencia;
import br.com.cliente.Endereco;

public class ServicosBcoBrasil {

	// o pedido tinha que ser um json
	public Boleto gerarBoleto(String pedido) {

		Object pedido = pedido.get("pedido");
		//Quem emite o boleto
		Beneficiario beneficiario = new Beneficiario();

		beneficiario.setNossoNumero(001); 
		beneficiario.setAgencia(1559);
		beneficiario.setDvAgencia(1);
		beneficiario.setCarteira(18);
		beneficiario.setCodigo(new Random().nextInt());
		beneficiario.setConvenio("Convenio Brasil Boleto");
		beneficiario.setNome(pedido.getLoja().getNome());
		beneficiario.setEndereco(pedido.getLoja().getEndereco);

		//Quem paga o boleto
		Pagador pagador = new Pagador();

		pagador.setNome(pedido.getUsuario().getNome());

		if(pedido.getUsuario().getCpf() != null) {
			pagador.setCpfCnpj(pedido.getUsuario().getCpf());
		}

		if(pedido.getUsuario().getCnpj() != null) {
			pagador.setCpfCnpj(pedido.getUsuario().getCnpj());
		}

		pagador.setEndereco(pedido.getUsuario().getEndereco());

		Boleto boleto = new Boleto(); 

		boleto.setAgencia(1559);
		boleto.setBeneficiario(beneficiario);
		boleto.setGeracao(new Data());
		boleto.setInstrucoes("Pagavel em qualquer agencia até o vencimento.");
		boleto.setLocalPagamento(Arrays.asList(LocalPgto.BANCODOBRASIL, LocalPgto.CASAS_LOTERICAS));
		boleto.setNumero(new Random().nextLong());
		boleto.setPagador(pagador);
		boleto.setProcessamento(new Data());
		boleto.setValor(pedido.getTotal());
		boleto.setVencimento(gerarVencimentoBoleto());

		//  GeradorDeBoleto gerador = new GeradorDeBoleto(boleto);
		return boleto;

	}

	private Data gerarVencimentoBoleto() {
		
		Data data = new Data();
		data.setDia(5);
		return data;
	}

	class Boleto {

		private int agencia;  
		private Data vencimento; 
		private Data processamento;
		private Data geracao;
		private Beneficiario beneficiario;  
		private Pagador pagador; 
		private BigDecimal valor; 
		private long numero;  
		private String instrucoes;  
		private List<LocalPgto> localPagamento;


		public int getAgencia() {
			return agencia;
		}

		public void setAgencia(int agencia) {
			this.agencia = agencia;
		}
		public Data getVencimento() {
			return vencimento;
		}
		public void setVencimento(Data vencimento) {
			this.vencimento = vencimento;
		}
		public Data getProcessamento() {
			return processamento;
		}
		public void setProcessamento(Data processamento) {
			this.processamento = processamento;
		}
		public Data getGeracao() {
			return geracao;
		}
		public void setGeracao(Data geracao) {
			this.geracao = geracao;
		}
		public Beneficiario getBeneficiario() {
			return beneficiario;
		}
		public void setBeneficiario(Beneficiario beneficiario) {
			this.beneficiario = beneficiario;
		}
		public Pagador getPagador() {
			return pagador;
		}
		public void setPagador(Pagador pagador) {
			this.pagador = pagador;
		}
		public BigDecimal getValor() {
			return valor;
		}
		public void setValor(BigDecimal valor) {
			this.valor = valor;
		}
		public long getNumero() {
			return numero;
		}
		public void setNumero(long numero) {
			this.numero = numero;
		}
		public String getInstrucoes() {
			return instrucoes;
		}
		public void setInstrucoes(String instrucoes) {
			this.instrucoes = instrucoes;
		}
		public List<LocalPgto> getLocalPagamento() {
			return localPagamento;
		}
		public void setLocalPagamento(List<LocalPgto> localPagamento) {
			this.localPagamento = localPagamento;
		}
	}

	class Beneficiario {

		private String nome;
		private Endereco endereco;
		private long nossoNumero;
		private String convenio;
		private long carteira;
		private int codigo;
		private int agencia;
		private int dvAgencia;


		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public Endereco getEndereco() {
			return endereco;
		}
		public void setEndereco(Endereco endereco) {
			this.endereco = endereco;
		}
		public long getNossoNumero() {
			return nossoNumero;
		}
		public void setNossoNumero(long nossoNumero) {
			this.nossoNumero = nossoNumero;
		}
		public String getConvenio() {
			return convenio;
		}
		public void setConvenio(String convenio) {
			this.convenio = convenio;
		}
		public long getCarteira() {
			return carteira;
		}
		public void setCarteira(long carteira) {
			this.carteira = carteira;
		}
		public int getCodigo() {
			return codigo;
		}
		public void setCodigo(int codigo) {
			this.codigo = codigo;
		}
		public int getAgencia() {
			return agencia;
		}
		public void setAgencia(int agencia) {
			this.agencia = agencia;
		}
		public int getDvAgencia() {
			return dvAgencia;
		}
		public void setDvAgencia(int dvAgencia) {
			this.dvAgencia = dvAgencia;
		}
	}

	class Pagador {

		private String nome;
		private String cpfCnpj;
		private Endereco endereco;

		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getCpfCnpj() {
			return cpfCnpj;
		}
		public void setCpfCnpj(String cpfCnpj) {
			this.cpfCnpj = cpfCnpj;
		}
		public Endereco getEndereco() {
			return endereco;
		}
		public void setEndereco(Endereco endereco) {
			this.endereco = endereco;
		}
	}

	class Data {

		private int dia;
		private int mes;
		private int ano;
		private int hora;
		private int minutos;
		private int segundos;

		public Data() {

			Calendar c = Calendar.getInstance();

			this.dia = c.get(Calendar.DAY_OF_MONTH);
			this.mes = c.get(Calendar.MONTH);
			this.ano = c.get(Calendar.YEAR);
			this.hora = c.get(Calendar.HOUR);
			this.minutos = c.get(Calendar.MINUTE);
			this.segundos = c.get(Calendar.SECOND);
		}

		public int getDia() {
			return dia;
		}

		public void setDia(int dia) {
			this.dia = dia;
		}

		public int getMes() {
			return mes;
		}

		public void setMes(int mes) {
			this.mes = mes;
		}

		public int getAno() {
			return ano;
		}

		public void setAno(int ano) {
			this.ano = ano;
		}

		public int getHora() {
			return hora;
		}

		public void setHora(int hora) {
			this.hora = hora;
		}

		public int getMinutos() {
			return minutos;
		}

		public void setMinutos(int minutos) {
			this.minutos = minutos;
		}

		public int getSegundos() {
			return segundos;
		}

		public void setSegundos(int segundos) {
			this.segundos = segundos;
		}


	}

	enum LocalPgto {

		CASAS_LOTERICAS, BANCODOBRASIL, ITAU, BRADESCO
	}
}

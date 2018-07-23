package br.com.BancoFinanceira;

import java.util.Arrays;

import br.com.cliente.Endereco;
import br.com.compra.Pedido;
import br.com.loja.Loja;
import simulador.servicos.externos.ServicosBcoBrasil;

public class BancoDoBrasil extends Agencia{

	public BancoDoBrasil() {
		
		this.numeroBanco = 001;
		this.nome = "Agencia Banco do Brasil";
		this.agencia = 1559;
		this.dvAgencia = 1;
		this.endereco = new Endereco("08577-400", "São Paulo", "SP", "Brasil");
		gerarConveniosDaLoja(); 

	}
	
	private void gerarConveniosDaLoja() {
		Convenio boleto = new Convenio(111256, "Convenio Brasil Boleto", new Loja(), TipoConvenio.BOLETO, this, new Conta(10181, 8, Loja.getNome()));
		Convenio credito = new Convenio(111257, "Convenio Brasil Credito", new Loja(), TipoConvenio.CARTAOCREDITO, this, new Conta(10182, 7, Loja.getNome()));
		Convenio debito = new Convenio(111258, "Convenio Brasil Debito", new Loja(), TipoConvenio.DEBITO, this, new Conta(10184, 0, Loja.getNome()));
		this.setConvenios(Arrays.asList(boleto, credito, debito));
	}
	
	@Override
	public  <T> T getInstanciaServico() {
	
		T servicos = (T) new ServicosBcoBrasil();
		return  servicos;
	}

	@Override
	public <T> T getBoleto(String pedido) {

		Object boleto = new ServicosBcoBrasil().gerarBoleto(pedido);
		return (T) boleto;
	}




}

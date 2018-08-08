package br.com.BancoFinanceira;

import java.util.Arrays;

import com.thoughtworks.xstream.XStream;

import Boleto.Boleto;
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
		Loja loja = new Loja();
		Convenio boleto = new Convenio(111256, "Convenio Brasil Boleto", loja, TipoConvenio.BOLETO, this, new Conta(10181, 8, loja.getNome()));
		Convenio credito = new Convenio(111257, "Convenio Brasil Credito", loja, TipoConvenio.CARTAOCREDITO, this, new Conta(10182, 7, loja.getNome()));
		Convenio debito = new Convenio(111258, "Convenio Brasil Debito", loja, TipoConvenio.DEBITO, this, new Conta(10184, 0, loja.getNome()));
		this.setConvenios(Arrays.asList(boleto, credito, debito));
	}
	
	@Override
	public  <T> T getInstanciaServico() {
	
		T servicos = (T) new ServicosBcoBrasil();
		return  servicos;
	}

	@Override
	public Boleto getBoleto(Pedido pedido) {

		XStream xs = new XStream();
		xs.alias("pedido", Pedido.class);
		String pedidoXml = xs.toXML(pedido);
		
		String boletoXml = new ServicosBcoBrasil().gerarBoleto(pedidoXml);
		
		Boleto boleto = (Boleto) xs.fromXML(boletoXml);
		
		return boleto;
	}




}

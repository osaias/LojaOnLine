package br.com.BancoFinanceira;

import java.util.Arrays;

import com.thoughtworks.xstream.XStream;

import Boleto.Boleto;
import Util.ServicoTransporte;
import Util.nomeTranspConverter;
import br.com.cliente.Endereco;
import br.com.compra.Pedido;
import br.com.compra.Produto;
import br.com.frete.Frete;
import br.com.loja.Loja;
import br.com.transportadora.Transportadora;
import simulador.servicos.externos.servicosBcoBrasil.ServicosBcoBrasil;

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
		xs.alias("produto", Produto.class);
		xs.aliasSystemAttribute("nome", "class");
		xs.aliasType("", Transportadora.class);
		xs.aliasField("transportadora", Frete.class, "transportador");
		xs.addDefaultImplementation(Frete.class,ServicoTransporte.class);
		xs.setMode(XStream.NO_REFERENCES);
		xs.registerLocalConverter(Frete.class, "transportador", new nomeTranspConverter());
		
		String pedidoXml = xs.toXML(pedido);
		
		String boletoXml = new ServicosBcoBrasil().gerarBoleto(pedidoXml);
		
		Boleto boleto = (Boleto) xs.fromXML(boletoXml);
		
		return boleto;
	}




}

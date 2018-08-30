package br.com.BancoFinanceira;

import java.io.FileNotFoundException;
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

		String pedidoXml = gerarXml(pedido);
		
		String boletoXml = new ServicosBcoBrasil().gerarBoleto(pedidoXml);
	 
		Boleto boleto = lerXML(boletoXml);
		
		return boleto;
	}

	private Boleto lerXML(String boletoXml) {

		XStream xs = new XStream();
		xs.alias("boleto", Boleto.class);
		xs.aliasField("pagador", Boleto.class, "sacado");
		xs.aliasField("beneficiario", Boleto.class, "cedente");
		xs.aliasField("emissao", Boleto.class, "processamento");
		xs.omitField(Boleto.class, "numeroBanco");
		xs.aliasField("agenciaBeneficiario", Boleto.class, "agencia");
		xs.aliasField("codigoBeneficiario", Boleto.class, "codigoCedente");
		xs.omitField(Boleto.class, "enderecoPagador");
		xs.addImplicitCollection(Boleto.class, "localPagamento", String.class);
		
		return  (Boleto) xs.fromXML(boletoXml);
	}

	private String gerarXml(Pedido pedido) {

		XStream xs = new XStream();
		xs.alias("pedido", Pedido.class);
		xs.alias("produto", Produto.class);
		xs.aliasSystemAttribute("nome", "class");
		xs.aliasType("", Transportadora.class);
		xs.aliasField("transportadora", Frete.class, "transportador");
		xs.addDefaultImplementation(Frete.class,ServicoTransporte.class);
		xs.setMode(XStream.NO_REFERENCES);
		xs.registerLocalConverter(Frete.class, "transportador", new nomeTranspConverter());
		
		return xs.toXML(pedido);
		
	}


	public static void main(String[] args) throws FileNotFoundException {

		String xml = "<boleto>" + 
				"  <numeroDocumento>18189159</numeroDocumento>" + 
				"  <numeroBanco>001</numeroBanco>" + 
				"  <agenciaBeneficiario>1559</agenciaBeneficiario>" + 
				"  <codigoBeneficiario>254879-1</codigoBeneficiario>" + 
				"  <codigoBarras>00188.3114 7223.194298 36925.719735 7 242659512417416</codigoBarras>" + 
				"  <carteira>06</carteira>" + 
				"  <enderecoPagador>" + 
				"    <numero>0</numero>" + 
				"    <cep>06915-520</cep>" + 
				"    <cidade>Mogi</cidade>" + 
				"    <uf>AP</uf>" + 
				"    <nacionalidade>Brasil</nacionalidade>" + 
				"  </enderecoPagador>" + 
				"  <vencimento>" + 
				"    <dia>35</dia>" + 
				"    <mes>7</mes>" + 
				"    <ano>2018</ano>" + 
				"    <hora>1</hora>" + 
				"    <minutos>3</minutos>" + 
				"    <segundos>25</segundos>" + 
				"  </vencimento>" + 
				"  <emissao>" + 
				"    <dia>30</dia>" + 
				"    <mes>7</mes>" + 
				"    <ano>2018</ano>" + 
				"    <hora>1</hora>" + 
				"    <minutos>3</minutos>" + 
				"    <segundos>25</segundos>" + 
				"  </emissao>" + 
				"  <beneficiario>" + 
				"    <nome>Loja Online Inc.</nome>" + 
				"    <numero>11100212</numero>" + 
				"    <endereco>" + 
				"      <numero>0</numero>" + 
				"      <cep>07600-100</cep>" + 
				"      <cidade>Mairiporã</cidade>" + 
				"      <uf>SP</uf>" + 
				"      <nacionalidade>Brasil</nacionalidade>" + 
				"    </endereco>" + 
				"    <codigo>254879</codigo>" + 
				"    <agencia>1559</agencia>" + 
				"    <dvAgencia>1</dvAgencia>" + 
				"  </beneficiario>" + 
				"  <pagador>" + 
				"    <nome>Osaias</nome>" + 
				"    <cpf>320.569.108-37</cpf>" + 
				"    <cnpj></cnpj>" + 
				"    <telefone>42563987</telefone>" + 
				"    <celular>945679857</celular>" + 
				"    <endereco>" + 
				"      <numero>0</numero>" + 
				"      <cep>06915-520</cep>" + 
				"      <cidade>Mogi</cidade>" + 
				"      <uf>AP</uf>" + 
				"      <nacionalidade>Brasil</nacionalidade>" + 
				"    </endereco>" + 
				"  </pagador>" + 
				"  <valor>11000.0</valor>" + 
				"  <nossoNumero>11100212</nossoNumero>" + 
				"  <instrucoes>Pagavel em qualquer agencia até o vencimento.</instrucoes>" + 
				"  <localPagamento>BANCODOBRASIL</localPagamento>" + 
				"  <localPagamento>CASAS_LOTERICAS</localPagamento>" + 
				"</boleto>";
		BancoDoBrasil bb = new BancoDoBrasil();
		
		Boleto boleto = bb.lerXML(xml);
		
		System.out.println(boleto);
		
	}


}

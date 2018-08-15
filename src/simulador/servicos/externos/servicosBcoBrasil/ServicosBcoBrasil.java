package simulador.servicos.externos.servicosBcoBrasil;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.thoughtworks.xstream.XStream;

public class ServicosBcoBrasil {

	public String gerarBoleto(String pedido){
		
		//pode ser usado qualquer biblioteca Stream, SAX, Java
		XStream xs = lerXml();
		
		Boleto boleto = (Boleto) xs.fromXML(pedido);
 
		boleto.getBeneficiario().setAgencia(1559);
		boleto.getBeneficiario().setDvAgencia(1);
		boleto.getBeneficiario().setCarteira(18);
		boleto.getBeneficiario().setCodigo(new Random().nextInt());
		boleto.getBeneficiario().setConvenio("Convenio Brasil Boleto");

		boleto.setGeracao(new Data());
		boleto.setInstrucoes("Pagavel em qualquer agencia até o vencimento.");
		boleto.setLocalPagamento(Arrays.asList(LocalPgto.BANCODOBRASIL, LocalPgto.CASAS_LOTERICAS));
		boleto.setNumero(new Random().nextLong());
		boleto.setProcessamento(new Data());
		boleto.setVencimento(gerarVencimentoBoleto());

		String xml = gerarXml(boleto);
		return xml;

	}

	private String gerarXml(Boleto boleto) {
		
		XStream xs = new XStream();
		xs.alias("boleto", Boleto.class);
		xs.aliasField("agencia", Beneficiario.class, "agencia");
		xs.aliasField("dvAgencia", Beneficiario.class, "dvAgencia");
		xs.omitField(Boleto.class, "endereco");
		xs.omitField(Boleto.class, "processamento");
		String xml = xs.toXML(boleto);
		return xml;
	}

	private XStream lerXml() {
		XStream xs = new XStream();
		xs.alias("pedido", Boleto.class);
		xs.aliasField("total", Boleto.class, "valor");
		xs.omitField(Boleto.class, "formaPgto");
		xs.omitField(Boleto.class, "status");
		xs.omitField(Boleto.class, "frete");
		xs.aliasField("enderecoEntrega", Boleto.class, "endereco");
		xs.aliasField("usuario", Boleto.class, "pagador");
		xs.omitField(Pagador.class, "id");
		xs.omitField(Pagador.class, "endereco");
		xs.omitField(Pagador.class, "logado");
		xs.omitField(Boleto.class, "produtos");
		xs.aliasField("loja", Boleto.class, "beneficiario");
		xs.aliasField("numero", Beneficiario.class, "nossoNumero");
		xs.omitField(Beneficiario.class, "endereco");
		return xs;
	}

	private Data gerarVencimentoBoleto() {
		
		Data data = new Data();
		data.setDia(data.getDia() + 5);
		return data;
	}

	enum LocalPgto {

		CASAS_LOTERICAS, BANCODOBRASIL
	}
	
	public static void main(String[] args) {
		ServicosBcoBrasil serv = new ServicosBcoBrasil();
		String pedido = "<pedido>" + 
				"  <numero>721715908</numero>" + 
				"  <total>2000.0</total>" + 
				"  <formaPgto>BOLETO</formaPgto>" + 
				"  <status>false</status>" + 
				"  <enderecoEntrega>" + 
				"    <numero>0</numero>" + 
				"    <cep>06915-520</cep>" + 
				"    <cidade>Mogi</cidade>" + 
				"    <uf>AP</uf>" + 
				"    <nacionalidade>Brasil</nacionalidade>" + 
				"  </enderecoEntrega>" + 
				"  <frete>" + 
				"    <cepOrigem>07600-100</cepOrigem>" + 
				"    <cepDestino>06915-520</cepDestino>" + 
				"    <peso>3250.0</peso>" + 
				"    <valorFrete>17.60</valorFrete>" + 
				"    <transportadora nome=\"\">Fedex</transportadora>" + 
				"    <servico>FEDEX_EXPRESS</servico>" + 
				"  </frete>" + 
				"  <usuario>" + 
				"    <id>1</id>" + 
				"    <nome>Osaias</nome>" + 
				"    <cpf>320.569.108-37</cpf>" + 
				"    <cnpj></cnpj>" + 
				"    <telefone>0</telefone>" + 
				"    <celular>0</celular>" + 
				"    <endereco>" + 
				"      <numero>0</numero>" + 
				"      <cep>06915-520</cep>" + 
				"      <cidade>Mogi</cidade>" + 
				"      <uf>AP</uf>" + 
				"      <nacionalidade>Brasil</nacionalidade>" + 
				"    </endereco>" + 
				"    <logado>false</logado>" + 
				"  </usuario>" + 
				"  <produtos>" + 
				"    <produto>" + 
				"      <id>1</id>" + 
				"      <nome>Tv Sony</nome>" + 
				"      <descricao>40&apos; Full HD Smart</descricao>" + 
				"      <fabricante>Sony</fabricante>" + 
				"      <preco>2000.0</preco>" + 
				"      <altura>100.0</altura>" + 
				"      <largura>10.0</largura>" + 
				"      <comprimento>150.0</comprimento>" + 
				"      <peso>3250.0</peso>" + 
				"    </produto>" + 
				"  </produtos>" + 
				"  <loja>" + 
				"    <nome>Loja Online Inc.</nome>" + 
				"    <numero>11100212</numero>" + 
				"    <endereco>" + 
				"      <numero>0</numero>" + 
				"      <cep>07600-100</cep>" + 
				"      <cidade>Mairiporã</cidade>" + 
				"      <uf>SP</uf>" + 
				"      <nacionalidade>Brasil</nacionalidade>" + 
				"    </endereco>" + 
				"  </loja>" + 
				"</pedido>";
		serv.gerarBoleto(pedido );
	}
}

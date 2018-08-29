package simulador.servicos.externos.servicosBcoBrasil;

import java.util.Arrays;
import java.util.Random;

import com.thoughtworks.xstream.XStream;

public class ServicosBcoBrasil {

	public String gerarBoleto(String pedido){
		
		Beneficiario beneficiario = new Beneficiario();
		Pagador pagador = new Pagador();
		
		XStream xs = lerXml();
		Boleto boleto = (Boleto) xs.fromXML(pedido);
 
		boleto.setNumeroBanco("001");
		boleto.setAgenciaBeneficiario(String.valueOf(beneficiario.getAgencia()));
		boleto.setCodigoBeneficiario(beneficiario.getCodigo() + "-" + beneficiario.getDvAgencia());
		boleto.getBeneficiario().setCodigo(beneficiario.getCodigo());
		boleto.getBeneficiario().setAgencia(beneficiario.getAgencia());
		boleto.getBeneficiario().setDvAgencia(beneficiario.getDvAgencia());
		boleto.setEmissao(new Data());
		boleto.setVencimento(gerarVencimentoBoleto());
		boleto.setNumeroDocumento(new Random().nextInt(99999999));
		boleto.setNossoNumero(boleto.getBeneficiario().getNumero());
		boleto.setCarteira(beneficiario.getCarteira() + "");
		boleto.setInstrucoes("Pagavel em qualquer agencia até o vencimento.");
		boleto.setLocalPagamento(Arrays.asList(LocalPgto.BANCODOBRASIL, LocalPgto.CASAS_LOTERICAS));
		boleto.setEnderecoPagador(boleto.getPagador().getEndereco());
		boleto.setCodigoBarras(boleto.getNumeroBanco() + new Random().nextInt(99) + "." + new Random().nextInt(99999) + 
				" " + new Random().nextInt(99999) +	"." + new Random().nextInt(999999) + 
				" " + new Random().nextInt(99999) +	"." + new Random().nextInt(999999) + 
				" " + new Random().nextInt(9) + " " + new Random().nextInt(99999999) + new Random().nextInt(99999999));

		String xml = gerarXml(boleto);
		return xml;

	}

	private String gerarXml(Boleto boleto) {
		
		XStream xs = new XStream();
		xs.alias("boleto", Boleto.class);
		xs.setMode(XStream.NO_REFERENCES);
		xs.alias("LocalPagamento", LocalPgto.class);
		xs.addImplicitArray(Boleto.class, "localPagamento");
		
		String xml = xs.toXML(boleto);
		return xml;
	}

	private XStream lerXml() {
		XStream xs = new XStream();
		xs.alias("pedido", Boleto.class);
		xs.aliasField("total", Boleto.class, "valor");
		xs.aliasField("loja", Boleto.class, "beneficiario");
		xs.aliasField("usuario", Boleto.class, "pagador");
		xs.omitField(Boleto.class, "formaPgto");
		xs.omitField(Boleto.class, "status");
		xs.omitField(Boleto.class, "frete");
		xs.omitField(Boleto.class, "numero");
		xs.omitField(Boleto.class, "enderecoEntrega");
		xs.omitField(Boleto.class, "produtos");
		xs.omitField(Pagador.class, "id");
		xs.omitField(Pagador.class, "logado");

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
				"      <cep>08577-520</cep>" + 
				"      <cidade>Itaqua</cidade>" + 
				"      <uf>SP</uf>" + 
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
		String boleto = serv.gerarBoleto(pedido );
		System.out.println(boleto);
	}
}

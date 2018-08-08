package br.com.BancoFinanceira;

import java.util.Arrays;

import Boleto.Boleto;
import br.com.cliente.Endereco;
import br.com.compra.Pedido;
import br.com.loja.Loja;

public class Itau extends Agencia{

	public Itau() {

		this.numeroBanco = 341;
		this.nome = "Agencia Banco do Itau";
		this.agencia = 8889;
		this.dvAgencia = 7;
		this.endereco = new Endereco("05810-400", "São Paulo", "SP", "Brasil");
		gerarConveniosDaLoja();
	}

	private void gerarConveniosDaLoja() {
		Loja loja = new Loja();
		Convenio credito = new Convenio(111257, "Convenio Itau Credito", loja, TipoConvenio.CARTAOCREDITO, this, new Conta(2182, 7, loja.getNome()));
		Convenio debito = new Convenio(111258, "Convenio Itau Debito", loja, TipoConvenio.DEBITO, this, new Conta(2184, 0, loja.getNome()));
		this.setConvenios(Arrays.asList(credito, debito));
	}
	

	@Override
	public <T> T getInstanciaServico() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boleto getBoleto(Pedido pedido) {
		// TODO Auto-generated method stub
		return null;
	}
}

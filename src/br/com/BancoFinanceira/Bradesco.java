package br.com.BancoFinanceira;

import java.util.Arrays;

import Boleto.Boleto;
import br.com.cliente.Endereco;
import br.com.compra.Pedido;
import br.com.loja.Loja;

public class Bradesco extends Agencia{

	public Bradesco() {

		this.numeroBanco = 237;
		this.nome = "Agencia Banco do Bradesco";
		this.agencia = 2369;
		this.dvAgencia = 0;
		this.endereco = new Endereco("03201-400", "São Paulo", "SP", "Brasil");
		gerarConveniosDaLoja();
	}

	private void gerarConveniosDaLoja() {
		Loja loja = new Loja();
		Convenio credito = new Convenio(111257, "Convenio Bradesco Credito", loja, TipoConvenio.CARTAOCREDITO, this, new Conta(101182, 7, loja.getNome()));
		Convenio debito = new Convenio(111258, "Convenio Bradesco Debito", loja, TipoConvenio.DEBITO, this, new Conta(101184, 0, loja.getNome()));
		this.setConvenios(Arrays.asList(credito, debito));
	}

	@Override
	public Boleto getBoleto(Pedido pedido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getInstanciaServico() {
		// TODO Auto-generated method stub
		return null;
	}

}

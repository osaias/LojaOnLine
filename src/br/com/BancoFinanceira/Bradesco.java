package br.com.BancoFinanceira;

import java.util.Arrays;

import br.com.cliente.Endereco;
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
		Convenio credito = new Convenio(111257, "Convenio Bradesco Credito", new Loja(), TipoConvenio.CARTAOCREDITO, this, new Conta(101182, 7, Loja.getNome()));
		Convenio debito = new Convenio(111258, "Convenio Bradesco Debito", new Loja(), TipoConvenio.DEBITO, this, new Conta(101184, 0, Loja.getNome()));
		this.setConvenios(Arrays.asList(credito, debito));
	}

	@Override
	public <T> T getBoleto(String pedido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getInstanciaServico() {
		// TODO Auto-generated method stub
		return null;
	}

}

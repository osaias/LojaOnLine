package br.com.DAO;

import br.com.cliente.Endereco;
import br.com.estoque.Estoque;

public class EstoqueDAO {

	public Endereco pegarEndereco() {
		
		return new Estoque().getEndereco();
	}
}

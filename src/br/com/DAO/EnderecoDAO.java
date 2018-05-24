package br.com.DAO;

import java.util.Map;

import br.com.cliente.Endereco;
import br.com.cliente.Usuario;
import simulador.BancoDados.TabelaEnderecos;

public class EnderecoDAO {

	public void cadastrar(Usuario usuario) {
		
		Map<Long, Endereco> enderecos = TabelaEnderecos.getEnderecos();
		
		enderecos.put(usuario.getId(), usuario.getEndereco());
	}
}

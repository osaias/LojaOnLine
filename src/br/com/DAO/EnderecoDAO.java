package br.com.DAO;

import java.util.Map;

import br.com.cliente.Endereco;
import br.com.cliente.Login;
import br.com.cliente.Usuario;
import simulador.BancoDados.TabelaEnderecos;
import simulador.BancoDados.TabelaLogin;

public class EnderecoDAO {

	public void cadastrar(Usuario usuario) {
		
		Map<Long, Endereco> enderecos = TabelaEnderecos.getEnderecos();
		
		enderecos.put(usuario.getId(), usuario.getEndereco());
	}
	
	public Endereco consultar(Usuario usuario) {
		
		Map<Long, Endereco> enderecos = TabelaEnderecos.getEnderecos();
		
		return enderecos.get(usuario.getId());
		
	}
}

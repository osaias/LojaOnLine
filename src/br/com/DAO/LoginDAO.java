package br.com.DAO;

import java.util.Map;

import br.com.cliente.Login;
import br.com.cliente.Usuario;
import simulador.BancoDados.TabelaLogin;

public class LoginDAO {

	public void cadastrar(Usuario usuario) {
		
		Map<Long, Login> logins = TabelaLogin.getLogins();
		
		logins.put(usuario.getId(), usuario.getLogin());
	}
	
	public Login consultar(Usuario usuario) {
		
		Map<Long, Login> logins = TabelaLogin.getLogins();
		
		return logins.get(usuario.getId());
		
	}
}

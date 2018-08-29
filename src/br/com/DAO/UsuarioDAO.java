package br.com.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.cliente.Usuario;
import simulador.BancoDados.TabelaUsuarios;

public class UsuarioDAO {

	public void cadastrar(Usuario usuario) {
		Map<String, Usuario> usuarios = TabelaUsuarios.getUsuarios();
		
		usuario.setId(usuarios.size() + 1l);

		if(!usuario.getCpf().isEmpty()) {

			usuarios.put(usuario.getCpf(), usuario);
		} else {
			usuarios.put(usuario.getCnpj(), usuario);
		}
	}

	public Usuario consultar(Usuario usuario) {

		Map<String, Usuario> usuarios = TabelaUsuarios.getUsuarios();

		if(!usuario.getCpf().isEmpty()) {

			return usuarios.get(usuario.getCpf());
		} else {
			return usuarios.get(usuario.getCnpj());
		}
	}
	
	public List<Usuario> getTodos() {
		
		List<Usuario> lista = new ArrayList<>();
		lista.addAll(TabelaUsuarios.getUsuarios().values());
		
		return lista;
	}
	

}

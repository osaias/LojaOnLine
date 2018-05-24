package simulador.BancoDados;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.cliente.Usuario;

public class TabelaUsuarios {

	private final static Map<String, Usuario> USUARIOS = new HashMap<>();
	
	static {
		geraIdEAdiciona(new Usuario("Osaias", "320.569.108-37"));
		geraIdEAdiciona(new Usuario("José", "53.085.405/0001-36"));
		geraIdEAdiciona(new Usuario("Matias", "13.319.458/0001-50"));
		geraIdEAdiciona(new Usuario("Rose", "71.999.364/0001-75"));
		geraIdEAdiciona(new Usuario("Maria", "100.559.108-11"));
		geraIdEAdiciona(new Usuario("Natan", "320.100.108-00"));
	}
	
	private static void geraIdEAdiciona(Usuario usuario) {
		
		usuario.setId(USUARIOS.size() + 1l);
		
		if(!usuario.getCpf().isEmpty()) {
			
			USUARIOS.put(usuario.getCpf(), usuario);
		} else {
			USUARIOS.put(usuario.getCnpj(), usuario);
		}
		
	}
	
	public static Map<String, Usuario> getUsuarios() {
		return USUARIOS;
	}

	public static void main(String[] args) {
		
		TabelaUsuarios.getUsuarios();
	}
}

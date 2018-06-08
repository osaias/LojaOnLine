package simulador.BancoDados;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.com.cliente.Login;
import br.com.cliente.Usuario;


public class TabelaLogin {

	private final static Map<Long, Login> LOGINS = new HashMap<>();
	
	static {
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.addAll(TabelaUsuarios.getUsuarios().values());
		
		geraLoginDoUsuario(new Login("jose.silveira@alura.com.br","123456"), usuarios.get(3));
		geraLoginDoUsuario(new Login("matias.turini@alura.com.br","6521458"), usuarios.get(0));
		geraLoginDoUsuario(new Login("admin@gmail.com","123"), usuarios.get(2));
		geraLoginDoUsuario(new Login("rose@gmail.com","128593"), usuarios.get(1));
		geraLoginDoUsuario(new Login("maria@gmail.com","125473"), usuarios.get(5));
		geraLoginDoUsuario(new Login("natan@gmail.com","125693"), usuarios.get(4));

	}
	
	private static void geraLoginDoUsuario(Login login, Usuario usuario){
		
		LOGINS.put(usuario.getId(), login);
	}
	
	public static Map<Long, Login> getLogins() {
		return LOGINS;
	}
}

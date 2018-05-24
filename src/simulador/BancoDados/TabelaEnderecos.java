package simulador.BancoDados;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.cliente.Endereco;
import br.com.cliente.Usuario;

public class TabelaEnderecos {

private final static Map<Long, Endereco> ENDERECOS = new HashMap<>();
	
	static {
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.addAll(TabelaUsuarios.getUsuarios().values());
		
		geraEndrecoDoUsuario(new Endereco("94901-520","Suzano","RS","Brasil"), usuarios.get(1));
		geraEndrecoDoUsuario(new Endereco("06915-520","Mogi","AP","Brasil"), usuarios.get(2));
		geraEndrecoDoUsuario(new Endereco("08577-520","Itaqua","SP","Brasil"), usuarios.get(0));
		geraEndrecoDoUsuario(new Endereco("23810-520","Penha","RJ","Brasil"), usuarios.get(3));
		geraEndrecoDoUsuario(new Endereco("61010-520","Santo Amaro","CE","Brasil"), usuarios.get(4));
		geraEndrecoDoUsuario(new Endereco("69910-520","Pinheiros","AC","Brasil"), usuarios.get(5));

	}
	
	private static void geraEndrecoDoUsuario(Endereco endereco, Usuario usuario){
		
		ENDERECOS.put(usuario.getId(), endereco);
	}
	
	public static Map<Long, Endereco> getEnderecos() {
		return ENDERECOS;
	}
}

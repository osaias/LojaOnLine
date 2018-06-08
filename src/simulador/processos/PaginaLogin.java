package simulador.processos;

import java.util.List;
import java.util.Scanner;

import Util.Console;
import Util.Sessao;
import br.com.DAO.LoginDAO;
import br.com.DAO.UsuarioDAO;
import br.com.cliente.Login;
import br.com.cliente.Usuario;

public class PaginaLogin {

	public boolean logar() {
		Scanner entrada = new Scanner(System.in);
		
		Console.limpatela();
		System.out.println("=================================================================================");
		System.out.println("                               PAGINA LOGIN                                      ");
		System.out.println("=================================================================================");
		System.out.print("\n Digite seu email: ");
		String email = entrada.nextLine();
		System.out.print(" Digite sua senha: ");
		String senha = entrada.nextLine();

		List<Usuario> usuarios = new UsuarioDAO().getTodos();
		LoginDAO loginDAO = new LoginDAO();
		
		for (Usuario usuario : usuarios) {
			
			Login login = loginDAO.consultar(usuario);
			
			if(login.getEmail().equals(email) && login.getSenha().equals(senha)) {
				
				Sessao.setAtributo("usuario",usuario);
				return true;
			}
		}

		return false;
	}
	
	public boolean verifica(Login dadosLogin) {

		if(dadosLogin.getEmail().equals(null)) {
			throw new IllegalArgumentException("O email está vazio");
		}

		if(dadosLogin.getSenha().equals(null)) {
			throw new IllegalArgumentException("A senha está vazia");
		}

		if(!dadosLogin.getEmail().equals("") 
				&& !dadosLogin.getSenha().equals("")){
			return true;
		}
		return false;
	}
	
}

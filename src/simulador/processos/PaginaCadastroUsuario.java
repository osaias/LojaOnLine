package simulador.processos;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Util.Console;
import br.com.DAO.EnderecoDAO;
import br.com.DAO.LoginDAO;
import br.com.DAO.UsuarioDAO;
import br.com.cliente.Endereco;
import br.com.cliente.Login;
import br.com.cliente.Usuario;
import simulador.BancoDados.TabelaLogin;

public class PaginaCadastroUsuario {

	public Usuario cadastrarUsuario() {

		Usuario usuario = new Usuario();
		Login login = new Login();
		Endereco endereco = new Endereco();
		
		Scanner entrada = new Scanner(System.in);

		Console.limpatela();
		System.out.println("----------------------------------------------------------");
		System.out.println("----------------------DADOS PESSOAIS----------------------");
		System.out.println("----------------------------------------------------------");
		System.out.println("Digite seu nome: ");
		usuario.setNome(entrada.nextLine());
		System.out.println("Digite seu sobrenome: ");
		usuario.setSobrenome(entrada.nextLine());
		System.out.println("Digite seu cpf: ");
		usuario.setCpf(entrada.nextLine());
		System.out.println("Digite seu telefone: ");
		usuario.setTelefone(Integer.parseInt(entrada.nextLine()));
		System.out.println("Digite seu celular: ");
		usuario.setCelular(Integer.parseInt(entrada.nextLine()));

		System.out.println("----------------------------------------------------------");
		System.out.println("-------------------------ENDEREÇO-------------------------");
		System.out.println("----------------------------------------------------------");
		System.out.println("Logradouro: ");
		endereco.setLogradouro(entrada.nextLine());
		System.out.println("Número: ");
		endereco.setNumero(Integer.parseInt(entrada.nextLine()));
		System.out.println("Cep: ");
		endereco.setCep(entrada.nextLine());
		System.out.println("Bairro: ");
		endereco.setBairro(entrada.nextLine());
		System.out.println("Cidade: ");
		endereco.setCidade(entrada.nextLine());
		System.out.println("UF: ");
		endereco.setUf(entrada.nextLine());
		System.out.println("Nacionalidade: ");
		endereco.setNacionalidade(entrada.nextLine());
		usuario.setEndereco(endereco);

		System.out.println("----------------------------------------------------------");
		System.out.println("-------------------------LOGIN----------------------------");
		System.out.println("----------------------------------------------------------");
		System.out.println("E-mail: ");
		login.setEmail(entrada.nextLine());
		System.out.println("Senha: ");
		login.setSenha(entrada.nextLine());
		usuario.setLogin(login);

		if(verifica(usuario.getLogin())) {
			usuario.setLogado(true);
		};
		
		new UsuarioDAO().cadastrar(usuario);
		new LoginDAO().cadastrar(usuario);
		new EnderecoDAO().cadastrar(usuario);
		
		entrada.close();

		return usuario;
	}

	private boolean verifica(Login dadosLogin) {

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
	
	public boolean logar() {
		Scanner entrada = new Scanner(System.in);
		
		Console.limpatela();
		System.out.println("Digite seu email: ");
		String email = entrada.nextLine();
		System.out.println("Digite sua senha: ");
		String senha = entrada.nextLine();
		
		//ir no banco de dados
		List<Usuario> usuarios = new UsuarioDAO().getTodos();
		LoginDAO loginDAO = new LoginDAO();
		
		for (Usuario usuario : usuarios) {
			
			Login login = loginDAO.consultar(usuario);
			
			if(login.getEmail().equals(email) && login.getSenha().equals(senha)) {
				
				return true;
			} else if (!login.getEmail().equals(email)) {
				
				throw new IllegalArgumentException("E-mail Inválido");
			} else if (!login.getSenha().equals(senha)) {
				
				throw new IllegalArgumentException("Senha Inválida");
			}
		}

		return false;
	}
	
	
	public static void main(String[] args) {
		 
		PaginaCadastroUsuario p = new PaginaCadastroUsuario();
		
		System.out.println(p.logar());
	}
}

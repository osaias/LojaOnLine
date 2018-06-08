package simulador.processos;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Util.Console;
import Util.Sessao;
import br.com.DAO.EnderecoDAO;
import br.com.DAO.LoginDAO;
import br.com.DAO.UsuarioDAO;
import br.com.cliente.Endereco;
import br.com.cliente.Login;
import br.com.cliente.Usuario;
import simulador.BancoDados.TabelaLogin;

public class PaginaCadastroUsuario {

	public boolean cadastrarUsuario() {

		Usuario usuario = new Usuario();
		Login login = new Login();
		Endereco endereco = new Endereco();
		PaginaLogin pagLogin = new PaginaLogin();
		
		Scanner entrada = new Scanner(System.in);

		Console.limpatela();
		System.out.println("=================================================================================");
		System.out.println("                              PAGINA CADASTRO                                    ");
		System.out.println("=================================================================================");
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("     DADOS PESSOAIS");
		System.out.println("---------------------------------------------------------------------------------");
		System.out.print("Digite seu nome: ");
		usuario.setNome(entrada.nextLine());
		System.out.print("Digite seu sobrenome: ");
		usuario.setSobrenome(entrada.nextLine());
		System.out.print("Digite seu cpf: ");
		usuario.setCpf(entrada.nextLine());
		System.out.print("Digite seu telefone: ");
		usuario.setTelefone(Integer.parseInt(entrada.nextLine()));
		System.out.print("Digite seu celular: ");
		usuario.setCelular(Integer.parseInt(entrada.nextLine()));

		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("     ENDEREÇO ");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.print("Logradouro: ");
		endereco.setLogradouro(entrada.nextLine());
		System.out.print("Número: ");
		endereco.setNumero(Integer.parseInt(entrada.nextLine()));
		System.out.print("Cep: ");
		endereco.setCep(entrada.nextLine());
		System.out.print("Bairro: ");
		endereco.setBairro(entrada.nextLine());
		System.out.print("Cidade: ");
		endereco.setCidade(entrada.nextLine());
		System.out.print("UF: ");
		endereco.setUf(entrada.nextLine());
		System.out.print("Nacionalidade: ");
		endereco.setNacionalidade(entrada.nextLine());
		usuario.setEndereco(endereco);

		System.out.println("------------------------------------------------------------------------------");
		System.out.println("     LOGIN ");
		System.out.println("------------------------------------------------------------------------------");
		System.out.print("E-mail: ");
		login.setEmail(entrada.nextLine());
		System.out.print("Senha: ");
		login.setSenha(entrada.nextLine());
		usuario.setLogin(login);

		if(pagLogin.verifica(usuario.getLogin())) {
			usuario.setLogado(true);
		};
		
		new UsuarioDAO().cadastrar(usuario);
		new LoginDAO().cadastrar(usuario);
		new EnderecoDAO().cadastrar(usuario);
		
		Sessao.setAtributo("usuario", usuario);
		
		entrada.close();

		return true;
	}

}

package simulador.processos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import Util.Console;
import br.com.DAO.ProdutoDAO;
import br.com.cliente.Usuario;
import br.com.compra.Produto;

public class PaginaPrincipal implements Logavel{
	
	List<Produto> listaProdutosPrincipal = new ArrayList<>();
	PaginaProduto linkPagProd = new PaginaProduto();
	PaginaCadastroUsuario linkPagCadUsuario = new PaginaCadastroUsuario();
	ProdutoDAO produtoDao = new ProdutoDAO();

	public Produto listarProdutos() {
		
		listaProdutosPrincipal = buscarListaProdutosPrincipal();

		System.out.println(String.format("%1$-3s %2$-25s %3$-9s", "ID", "NOME", "VALOR"));
		System.out.println("---------------------------------------------------------------------------------");
		for (Produto p : listaProdutosPrincipal) {

			System.out.println(String.format("%1$-3d %2$-25s %3$.2f", p.getId(), p.getNome().toUpperCase(), p.getPreco()));
		}
		System.out.println("---------------------------------------------------------------------------------");
		Scanner entrada = new Scanner(System.in);
		System.out.println("ID Produto: ");
		long id = Long.parseLong(entrada.nextLine());
		
		for (Produto p : listaProdutosPrincipal) {

			if (p.getId() == id) {
				return p;
			}
		}
		return null;

	}
	
	@Override
	public boolean logar() {
		
		return linkPagCadUsuario.logar();
	}
	
	@Override
	public Usuario cadastrarUsuario() {
		
		return linkPagCadUsuario.cadastrarUsuario();
	}
	
	public void clicar(Produto produto) {
		if(linkPagProd.exibirProduto(produto)) {
			
		}
	}

	public String cadastrarEmail() {
		
		Scanner entrada = new Scanner(System.in);
		System.out.println("Seu email: ");
		String email = entrada.nextLine();
		
		//logica de ir no banco
		//usado quando usuario só quer receber as news.
		return "Email cadastrado com sucesso";
	}
	
	public Produto pesquisar(Produto produto) {

		return produtoDao.buscar(produto);
	}
	
	private List<Produto> buscarListaProdutosPrincipal(){

		//mexer aqi
		List<Produto> produtos = new ArrayList<>();
		List<Produto> lista = produtoDao.getTodosProdutos();

		produtos.add(0, lista.get(0));
		
		for (Produto produto : lista) {
			if (!produtos.get(produtos.size()-1).getNome().equals(produto.getNome())) {
				produtos.add(produto);
			}

		}
	
		lista.clear();
		lista.addAll(produtos);
		
		return lista;
	}
	
	/*public static void main(String[] args) {
		
		PaginaPrincipal pp = new PaginaPrincipal();
		//Usuario usuario = pp.cadastrarUsuario();
		//System.out.println(pp.cadastrarUsuario().getNome());
		Console.limpatela();
		pp.listarProdutos();

	}*/
}

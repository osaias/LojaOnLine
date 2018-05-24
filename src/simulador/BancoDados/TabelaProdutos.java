package simulador.BancoDados;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import br.com.compra.Produto;
import br.com.estoque.Categorias;


public final class TabelaProdutos {

	private final static Map<Long, Produto> PRODUTOS = new HashMap<>();
	private final static Map<Categorias, List<Produto>> CATEGORIAS = new HashMap<>();
	
	static {

		List<Produto> lista = new ArrayList<>();
		
		for(int i=0; i < new Random().nextInt(50)+1; i++) {
			lista.add(geraIdEAdiciona(new Produto("Tv Sony", "40' Full HD Smart","Sony", 2000.00, 100.0, 10.0, 150.00, 3250.00)));
		}
		categorizar(Categorias.TV,lista); 
		lista.clear();
		
		for(int i=0; i < new Random().nextInt(50)+1; i++) {
			lista.add(geraIdEAdiciona(new Produto("Hd Samsung", "1TB 7200rpm usb3.0","Samsung", 220.00, 1.0, 7.0, 14.0, 80.0)));
		}
		categorizar(Categorias.HD,lista); 
		lista.clear();
		
		for(int i=0; i < new Random().nextInt(50)+1; i++) {
			lista.add(geraIdEAdiciona(new Produto("Smartphone Motorola","16Gb 1.5Ghz 4G","Motorola", 1500.00, 0.05, 6.5, 14.92, 70.0)));
		}
		categorizar(Categorias.SMARTPHONE,lista); 
		lista.clear();
		
		for(int i=0; i < new Random().nextInt(50)+1; i++) {
			lista.add(geraIdEAdiciona(new Produto("celular Alcatel","3G 1GB Preto","Alcatel", 450.00, 0.05, 7.2, 17.0, 120.0)));
		}
		categorizar(Categorias.SMARTPHONE,lista); 
		lista.clear();
		
		for(int i=0; i < new Random().nextInt(50)+1; i++) {
			lista.add(geraIdEAdiciona(new Produto("notebook Dell","I7 6.0GHZ 8GB DDR3 ","Dell", 2800.00, 3.0, 20.0,30.0, 1350.0)));
		}
		categorizar(Categorias.NOTEBOOK,lista); 
		lista.clear();
		
		for(int i=0; i < new Random().nextInt(50)+1; i++) {
			lista.add(geraIdEAdiciona(new Produto("Mouse Logitech","preto usb com led","Logitech", 12.00, 4.0, 3.3, 5.5, 30.25)));
		}
		categorizar(Categorias.MOUSE,lista); 
		lista.clear();
		
		for(int i=0; i < new Random().nextInt(50)+1; i++) {
			lista.add(geraIdEAdiciona(new Produto("Teclado Lenovo", "preto usb","Lenovo", 17.00, 3.0, 20.0, 40.5, 300.0)));
		}
		categorizar(Categorias.TECLADO,lista); 
		lista.clear();
		
		for(int i=0; i < new Random().nextInt(50)+1; i++) {
			lista.add(geraIdEAdiciona(new Produto("monitor Dell", "15' touch screen preto ","Dell", 800.00, 32.0, 5.0, 40.0, 2000.0)));
		}
		categorizar(Categorias.MONITOR,lista); 
		lista.clear();
		
		for(int i=0; i < new Random().nextInt(50)+1; i++) {
			lista.add(geraIdEAdiciona(new Produto("Som Sony", "400watts RMS 2 auto-falantes 8' ", "Sony", 960.00, 60.0, 35.1, 80.5, 1900.25)));
		}
		categorizar(Categorias.SOM,lista); 
		lista.clear();
		
		for(int i=0; i < new Random().nextInt(50)+1; i++) {
			lista.add(geraIdEAdiciona(new Produto("Relogio Mormaii", "digital prova dágua 50mts profundidade","Mormaii", 180.00, 10.0, 5.1, 9.5, 23.25)));
		}
		categorizar(Categorias.RELOGIO,lista); 
		lista.clear();
		
		for(int i=0; i < new Random().nextInt(50)+1; i++) {
			lista.add(geraIdEAdiciona(new Produto("Refrigerador Consul", "Refrigerador Duplex", "Consul", 1800.00, 170.0, 65.1, 80.5, 18.25)));
		}
		categorizar(Categorias.REFRIGERADOR,lista); 
		lista.clear();
		
		for(int i=0; i < new Random().nextInt(50)+1; i++) {
			lista.add(geraIdEAdiciona(new Produto("Microondas Electrolux", "Microondas 60lts", "Electrolux", 400.00, 46.0, 54.1, 74.5, 8.25)));
		}
		categorizar(Categorias.MICROONDAS,lista); 
		lista.clear();
		
	}
	
	
	private static Produto geraIdEAdiciona(Produto produto) {

		produto.setId(PRODUTOS.size() + 1l);
		PRODUTOS.put(produto.getId(), produto);
		
		return produto;
	}

	private static void categorizar(Categorias categoria, List<Produto> produtos) {
		
		CATEGORIAS.put(categoria, produtos);
	}

	public static Collection<Produto> getProdutos() {
		return PRODUTOS.values();
	}
	
	
	public static Map<Categorias, List<Produto>> getCategorias() {
		return CATEGORIAS;
	}

	public static void main(String[] args) {
		Produto produto = new Produto("Tv Sony", "40' Full HD Smart", "Sony", 2000.00, 100.0, 10.0, 150.00, 3250.00);

		//TabelaProdutos.geraIdEAdiciona(produto );
	}

}

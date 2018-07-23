package Util;


public interface ServicoBancario extends Servico {

	<T> T getBoleto(String pedido);

}

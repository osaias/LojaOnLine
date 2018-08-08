package Util;

import Boleto.Boleto;
import br.com.compra.Pedido;

public interface ServicoBancario extends Servico {

	Boleto getBoleto(Pedido pedido);

}

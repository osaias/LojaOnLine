package Util;

import com.thoughtworks.xstream.converters.SingleValueConverter;

import br.com.transportadora.CorreiosTransportes;
import br.com.transportadora.Transportadora;

public class nomeTranspConverter implements SingleValueConverter{

	@Override
	public boolean canConvert(Class type) {
		
		System.out.println();
		boolean assignableFrom = ServicoTransporte.class.isAssignableFrom(type);
		return assignableFrom;
	}

	@Override
	public Object fromString(String transportadora) {

		ServicoTransporte transp = new CorreiosTransportes(transportadora);
			
		return transp;
	}

	@Override
	public String toString(Object transportadora) {

		Transportadora transp = (Transportadora) transportadora;
		
		return transp.getNome();
	}

}

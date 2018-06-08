package Util;

import java.util.HashMap;
import java.util.Map;

public class Sessao {

	private static Map<String,Object> atributos = new HashMap<String, Object>();

	public static Map<String, Object> getAtributos() {
		return atributos;
	}

	public static void setAtributo(String atributo, Object objeto) {
		Sessao.atributos.put(atributo, objeto);
	}
	
	public static Object getAtributo(String name){
		
		return atributos.get(name);
	}
}

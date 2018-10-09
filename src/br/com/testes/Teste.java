package br.com.testes;

public class Teste {

	public static void main(String[] args) {
		String n = "60005";
		int combinacoes = 1;
		int validos = 0;
		int invalidos = 0;

		for (int i = 0; i < n.length(); i++) {
			if (n.charAt(i) != '0') {
				validos++;
			} else {
				invalidos++;
			}

		}

		for (int j = 1; j <= validos; j++) {
			combinacoes = combinacoes * j;
		}

		if (invalidos > 0) {
			combinacoes = validos * combinacoes;
		}

		System.out.println(combinacoes);

	}

}




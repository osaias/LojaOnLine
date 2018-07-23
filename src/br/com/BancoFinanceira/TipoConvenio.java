package br.com.BancoFinanceira;

public enum TipoConvenio {

	BOLETO(new Agencia[] { new BancoDoBrasil() }),
	CARTAOCREDITO(new Agencia[] { new BancoDoBrasil(), new Bradesco(), new Itau() } ),
	DEBITO(new Agencia[] { new BancoDoBrasil(), new Bradesco(), new Itau() });
	
	private final Agencia[] agencias;

	private TipoConvenio(Agencia[] agencias) {
		this.agencias = agencias;
	}

	public Agencia[] getAgencias() {
		return agencias;
	}
	
	public static void main(String[] args) {
		TipoConvenio boleto2 = TipoConvenio.BOLETO;
		Agencia[] agencias2 = boleto2.getAgencias();
		String name2 = boleto2.name();
		
		TipoConvenio[] values = TipoConvenio.values();
		
		String name3 = TipoConvenio.CARTAOCREDITO.name();
		
		System.out.println();
	}
}

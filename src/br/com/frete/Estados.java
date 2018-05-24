package br.com.frete;

public enum Estados {


    ACRE("Acre", "AC", Regioes.NORTE),

    ALAGOAS("Alagoas", "AL", Regioes.NORDESTE),

    AMAPA("Amap�", "AP", Regioes.NORTE),

    AMAZONAS("Amazonas", "AM", Regioes.NORTE),

    BAHIA("Bahia", "BA", Regioes.NORDESTE),
 
    CEARA("Cear�", "CE", Regioes.NORDESTE),

    DISTRITOFEDERAL("Distrito Federal", "DF", Regioes.CENTROOESTE),
 
    ESPIRITOSANTO("Esp�rito Santo", "ES", Regioes.SUDESTE),

    GOIAS("Goi�s", "GO", Regioes.CENTROOESTE),
 
    MARANHAO("Maranh�o", "MA", Regioes.NORDESTE),

    MATOGROSSO("Mato Grosso", "MT", Regioes.CENTROOESTE),

    MATOGROSSODOSUL("Mato Grosso do Sul", "MS", Regioes.CENTROOESTE),

    MINASGERAIS("Minas Gerais", "MG", Regioes.SUDESTE),
  
    PARA("Par�", "PA", Regioes.NORTE),
 
    PARAIBA("Para�ba", "PB", Regioes.NORDESTE),
 
    PARANA("Paran�", "PR", Regioes.SUL),

    PERNAMBUCO("Pernambuco", "PE", Regioes.NORDESTE),

    PIAUI("Piau�", "PI", Regioes.NORDESTE),

    RIODEJANEIRO("Rio de Janeiro", "RJ", Regioes.SUDESTE),

    RIOGRANDEDONORTE("Rio Grande do Norte", "RN", Regioes.NORDESTE),
  
    RIOGRANDEDOSUL("Rio Grande do Sul", "RS", Regioes.SUL),
 
    RONDONIA("Rond�nia", "RO", Regioes.NORTE),
   
    RORAIMA("Roraima", "RR", Regioes.NORTE),
  
    SANTACATARINA("Santa Catarina", "SC", Regioes.SUL),
  
    SAOPAULO("S�o Paulo", "SP", Regioes.SUDESTE),
   
    SERGIPE("Sergipe", "SE", Regioes.NORDESTE),
 
    TOCANTINS("Tocantins", "TO", Regioes.NORTE);
    private String nome;
    private String sigla;
    private Regioes regiao;
    

    private Estados(String nome, String sigla, Regioes regiao) {
        this.nome = nome;
        this.sigla = sigla;       
        this.regiao = regiao;
    }

    public String getNome() {
        return nome;
    }

    /**
     * Retorna sigla
     *
     * @return sigla
     */
    public String getSigla() {
        return sigla;
    }

    /**
     * Retorna regi�o
     * @return regiao
     */
    public Regioes getRegiao() {
        return regiao;
    }
}

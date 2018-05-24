package br.com.frete;

public enum Estados {


    ACRE("Acre", "AC", Regioes.NORTE),

    ALAGOAS("Alagoas", "AL", Regioes.NORDESTE),

    AMAPA("Amapá", "AP", Regioes.NORTE),

    AMAZONAS("Amazonas", "AM", Regioes.NORTE),

    BAHIA("Bahia", "BA", Regioes.NORDESTE),
 
    CEARA("Ceará", "CE", Regioes.NORDESTE),

    DISTRITOFEDERAL("Distrito Federal", "DF", Regioes.CENTROOESTE),
 
    ESPIRITOSANTO("Espírito Santo", "ES", Regioes.SUDESTE),

    GOIAS("Goiás", "GO", Regioes.CENTROOESTE),
 
    MARANHAO("Maranhão", "MA", Regioes.NORDESTE),

    MATOGROSSO("Mato Grosso", "MT", Regioes.CENTROOESTE),

    MATOGROSSODOSUL("Mato Grosso do Sul", "MS", Regioes.CENTROOESTE),

    MINASGERAIS("Minas Gerais", "MG", Regioes.SUDESTE),
  
    PARA("Pará", "PA", Regioes.NORTE),
 
    PARAIBA("Paraíba", "PB", Regioes.NORDESTE),
 
    PARANA("Paraná", "PR", Regioes.SUL),

    PERNAMBUCO("Pernambuco", "PE", Regioes.NORDESTE),

    PIAUI("Piauí", "PI", Regioes.NORDESTE),

    RIODEJANEIRO("Rio de Janeiro", "RJ", Regioes.SUDESTE),

    RIOGRANDEDONORTE("Rio Grande do Norte", "RN", Regioes.NORDESTE),
  
    RIOGRANDEDOSUL("Rio Grande do Sul", "RS", Regioes.SUL),
 
    RONDONIA("Rondônia", "RO", Regioes.NORTE),
   
    RORAIMA("Roraima", "RR", Regioes.NORTE),
  
    SANTACATARINA("Santa Catarina", "SC", Regioes.SUL),
  
    SAOPAULO("São Paulo", "SP", Regioes.SUDESTE),
   
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
     * Retorna região
     * @return regiao
     */
    public Regioes getRegiao() {
        return regiao;
    }
}

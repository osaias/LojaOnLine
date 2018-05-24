package br.com.frete;

public enum Ceps {

	SP_CAPITAL(Estados.SAOPAULO, "Capital", "01000-000" , "05999-000"),
	SP_GRANDESP(Estados.SAOPAULO, "Região Metropolitana de São Paulo ou Grande São Paulo", "06000-000" , "09999-000"),
	SP_LITORAL(Estados.SAOPAULO, "Litoral paulista", "11000-000", "11999-000"),
	SP_INTERIOR(Estados.SAOPAULO, "Interior paulista", "12000-000", "19999-000"),
	
	RJ_CAPITAL(Estados.RIODEJANEIRO, "Capital", "20000-000" , "23799-000"),
	RJ_GRANDERIO(Estados.RIODEJANEIRO, "Região Metropolitana do Rio de Janeiro ou Grande Rio", "23800-000", "26600-000"),
	RJ_INTERIOR(Estados.RIODEJANEIRO, "Interior fluminense e baixada fluminense", "26601-000", "28999-000"),
	
	ES_CAPITAL(Estados.ESPIRITOSANTO, "Capital", "29000-000", "29099-000"),
	ES_INTERIOR(Estados.ESPIRITOSANTO, "Interior capixaba", "29100-000", "29999-000"),
	
	MG_CAPITAL(Estados.MINASGERAIS, " Capital Belo Horizonte", "30000-000", "31999-000"),
	MG_GRANDEBH(Estados.MINASGERAIS, "Região Metropolitana de Belo Horizonte (Grande BH)", "32000-000", "34999-000"),
	MG_INTERIOR(Estados.MINASGERAIS, "Interior Mineiro", "35000-000", "39999-000"),
	
	BA_CAPITAL(Estados.BAHIA, "Capital Salvador (BA)", "40000-000", "41999-000"),
	BA_GRANDESALVADOR(Estados.BAHIA, "Região Metropolitana de Salvador ou Grande Salvador", "42000-000", "44470-000"),
	BA_INTERIOR(Estados.BAHIA, "Interior Baiano", "44471-000", "48999-000"),
	
	SE_CAPITAL(Estados.SERGIPE, "Capital Aracaju (SE)", "49000-000", "49099-000"),
	SE_INTERIOR(Estados.SERGIPE, "Interior Sergipano", "49100-000", "49999-000"),
	
	PE_CAPITAL(Estados.PERNAMBUCO, "Capital	Recife", "50000-000", "52999-000"),
	PE_GRANDERECIFE(Estados.PERNAMBUCO, "Região Metropolitana de Recife ou Grande Recife", "53000-000", "54999-000"),
	PE_INTERIOR(Estados.PERNAMBUCO, "Interior pernambucano", "55000-000", "56999-000"),
	
	AL_CAPITAL(Estados.ALAGOAS, "Capital Maceió (AL)", "57000-000", "57099-000"),
	AL_INTERIOR(Estados.ALAGOAS, "Interior alagoano", "57100-000", "57999-000"),
	
	PB_CAPITAL(Estados.PARAIBA, "Capital João Pessoa (PB)", "58000-000", "58099-000"),
	PB_INTERIOR(Estados.PARAIBA, "Interior paraibano", "58100-000", "58999-000"),
	
	RN_CAPITAL(Estados.RIOGRANDEDONORTE, " Capital Natal (RN)", "59000-000", "59099-000"),
	RN_INTERIOR(Estados.RIOGRANDEDONORTE, "Interior potiguar", "59100-000" , "59999-000"),
	
	CE_CAPITAL(Estados.CEARA, "Capital Fortaleza (CE)", "60000-000", "60999-000"),
	CE_GRANDEFORTALEZA(Estados.CEARA, "Região Metropolitana de Fortaleza ou Grande Fortaleza", "61000-000", "61900-000"),
	CE_INTERIOR(Estados.CEARA, "Interior cearense", "61901-000" , "63999-000"),
	
	PI_CAPITAL(Estados.PIAUI, "Capital Teresina (PI)", "64000-000", "64099-000"),
	PI_INTERIOR(Estados.PIAUI, "Interior piauiense" , "64100-000", "64999-000"),
	
	MA_CAPITAL(Estados.MARANHAO, "Capital São Luiz (MA)", "65000-000", "65099-000"),
	MA_INTERIOR(Estados.MARANHAO, "Interior maranhense", "65100-000", "65999-000"),
	
	PA_CAPITAL(Estados.PARA, " Capital Belém (PA)", "66000-000", "66999-000"),
	PA_GRANDEBELEM(Estados.PARA, " Região Metropolitana de Belém", "67000-000", "67999-000"),
	PA_INTERIOR(Estados.PARA, "Interior paraense", "68000-000" ,"68899-000"),
	
	AP_CAPITAL(Estados.AMAPA, "Capital Macapá (AP)", "68900-000", "68914-000"),
	AP_INTERIOR(Estados.AMAPA, "Interior amapense", "68915-000", "68999-000"),
	
	AM_CAPITAL(Estados.AMAZONAS, "Capital Manaus", "69000-000", "69099-000"),
	AM_INTERIOR(Estados.AMAZONAS, "Interior amazonense", "69100-000" , "69299-000"),
	
	RR_CAPITAL(Estados.RORAIMA, "Capital Boa Vista (RR)", "69300-000", "69339-000"),
    RR_INTERIOR(Estados.RORAIMA,"Interior roraimense",	"69340-000", "69899-000"),
	
	AC_CAPITAL(Estados.ACRE, "Capital Rio Branco (AC)", "69900-000", "69920-000"),
	AC_INTERIOR(Estados.ACRE, "Interior acriano", "69921-000", "69999-000"),
	
	DF_CAPITAL(Estados.DISTRITOFEDERAL, "Capital Brasília", "70000-000", "70999-000"),
	DF_SATELITE(Estados.DISTRITOFEDERAL, "Cidades Satélite de Brasília", "71000-000", "73699-000"),
	
	GO_CAPITAL(Estados.GOIAS, "Capital Goiânia (GO)", "72800-000", "73999-000"),
	GO_GRANDEGOIANIA(Estados.GOIAS, "Região metropolitana Goiânia (GO)", "74000-000", "74894-000"),
	GO_INTERIOR(Estados.GOIAS, "Interior goianiense", "74895-000", "76799-000"),
	
	TO_CAPITAL(Estados.TOCANTINS, "Capital Palmas (TO)", "77000-000", "77270-000"),
	TO_INTERIOR(Estados.TOCANTINS, "Interior tocantinense", "77271-000", "77995-000"),
	
	MT_CAPITAL(Estados.MATOGROSSO, "Capital Cuiabá (MT)", "78000-000", "78109-000"),
	MT_INTERIOR(Estados.MATOGROSSO, "Interior mato-grossense", "78110-000", "78899-000"),
	
	RO_CAPITAL(Estados.RONDONIA, "Capital Porto Velho (RO)", "78900-000", "78930-000"),
	RO_INTERIOR(Estados.RONDONIA, "Interior rondoniense", "78931-000", "78999-000"),
	
	MS_CAPITAL(Estados.MATOGROSSODOSUL, "Capital Campo Grande (MS)", "79000-000", "79129-000"),
	MS_INTERIOR(Estados.MATOGROSSODOSUL, "Interior sul-mato-grossense", "79130-000", "79999-000"),
	
	PR_CAPITAL(Estados.PARANA, "Capital Curitiba (PR)", "80000-000", "82999-000"),
	PR_GRANDECURITIBA(Estados.PARANA, "Região Metropolitana de Curitiba ou Grande Curitiba", "80000-000", "83800-000"),
	PR_INTERIOR(Estados.PARANA, "Interior paranaense", "83801-000", "87999-000"),
	
	SC_CAPITAL(Estados.SANTACATARINA ,"Capital Florianópolis (SC)", "88000-000", "88100-000"),
	SC_GRANDEFLORIANOPOLIS(Estados.SANTACATARINA ,"Região Metropolitana de Florianópolis ou Grande Florianópolis", "88000-000", "88469-000"),
	SC_INTERIOR(Estados.SANTACATARINA, "Interior catarinense" , "88470-000", "89999-000"),
	
	RS_CAPITAL(Estados.RIOGRANDEDOSUL, "Capital Porto Alegre (RS)", "90000-000", "91999-000"),
	RS_GRANDEPORTOALEGRE(Estados.RIOGRANDEDOSUL, "Região Metropolitana de Porto Alegre ou Grande Poá", "90000-000", "94900-000"),
	RS_INTERIOR(Estados.RIOGRANDEDOSUL, "Interior Sul Rio Grandense", "94901-000" , "99999-000");
	
	private Estados estado;
	private String local;
	private String cepInicio;
	private String cepFim;
	
	private Ceps(Estados estado, String local, String cepInicio, String cepFim) {
		this.estado = estado;
		this.local = local;
		this.cepInicio = cepInicio;
		this.cepFim = cepFim;
	}

	public Estados getEstado() {
		return estado;
	}

	public String getLocal() {
		return local;
	}

	public String getCepInicio() {
		return cepInicio;
	}

	public String getCepFim() {
		return cepFim;
	}
	
	
}

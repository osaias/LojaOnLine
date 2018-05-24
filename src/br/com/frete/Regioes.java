package br.com.frete;

import java.util.ArrayList;
import java.util.List;

public enum Regioes implements IEstadosPorRegiaoStrategy{

    CENTROOESTE("Centro-Oeste") {
        @Override
        public List<Estados> getEstados() {
            return estadosPorRegiao(CENTROOESTE);
        }

    },
  
    NORDESTE("Nordeste") {
        @Override
        public List<Estados> getEstados() {
            return estadosPorRegiao(NORDESTE);
        }
    },

    NORTE("Norte") {
        @Override
        public List<Estados> getEstados() {
            return estadosPorRegiao(NORTE);
        }
    },
  
    SUDESTE("Sudeste") {
        @Override
        public List<Estados> getEstados() {
            return estadosPorRegiao(SUDESTE);
        }
    },

    SUL("Sul") {
        @Override
        public List<Estados> getEstados() {
            return estadosPorRegiao(SUL);
        }
    };
  
    private final String nome;


    private Regioes(String nome) {
        this.nome = nome;
    }


    public String getNome() {
        return nome;
    }

    protected List<Estados> estadosPorRegiao(Regioes regiao) {
        List<Estados> lista = new ArrayList<>();

        for (Estados value : Estados.values()) {
            if (value.getRegiao().equals(regiao)) {
                lista.add(value);
            }
        }
        return lista;
    }
    
    protected Estados[] listaDeEstados(){

    	return Estados.values();
    }

}

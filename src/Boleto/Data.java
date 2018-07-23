package Boleto;

import java.util.Calendar;

public class Data {

	private int dia;
	private int mes;
	private int ano;
	private int hora;
	private int minutos;
	private int segundos;
	
	public Data() {
		
		Calendar c = Calendar.getInstance();
		
		this.dia = c.get(Calendar.DAY_OF_MONTH);
		this.mes = c.get(Calendar.MONTH);
		this.ano = c.get(Calendar.YEAR);
		this.hora = c.get(Calendar.HOUR);
		this.minutos = c.get(Calendar.MINUTE);
		this.segundos = c.get(Calendar.SECOND);
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public int getSegundos() {
		return segundos;
	}

	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}
	
	
}

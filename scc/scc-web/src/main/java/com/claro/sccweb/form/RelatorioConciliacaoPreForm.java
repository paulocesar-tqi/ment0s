package com.claro.sccweb.form;

public class RelatorioConciliacaoPreForm extends BaseForm {

	private String operadoraClaro;
	private String operadoraExterna;
	private String plataforma;
	private Long mes;	
	private Long ano;
	
	public String getOperadoraClaro() {
		return operadoraClaro;
	}
	public void setOperadoraClaro(String operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}
	public String getOperadoraExterna() {
		return operadoraExterna;
	}
	public void setOperadoraExterna(String operadoraExterna) {
		this.operadoraExterna = operadoraExterna;
	}
	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	public Long getMes() {
		return mes;
	}
	public void setMes(Long mes) {
		this.mes = mes;
	}
	public Long getAno() {
		return ano;
	}
	public void setAno(Long ano) {
		this.ano = ano;
	}
	
	
}

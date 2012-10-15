package com.claro.cobillingweb.persistence.filtro;

import java.util.Date;

public class SccFiltroRelPerdaFaturamento {
	
	private boolean holding;
	
	private String fileType;
	
	private String operadoraExterna;
	
	private String operadoraClaro;
	
	private Date dataInicialPeriodo;
	
	private Date dataFinalPeriodo;
	
	private String evento;

	public boolean isHolding() {
		return holding;
	}

	public void setHolding(boolean holding) {
		this.holding = holding;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getOperadoraExterna() {
		return operadoraExterna;
	}

	public void setOperadoraExterna(String operadoraExterna) {
		this.operadoraExterna = operadoraExterna;
	}

	public String getOperadoraClaro() {
		return operadoraClaro;
	}

	public void setOperadoraClaro(String operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}

	public Date getDataInicialPeriodo() {
		return dataInicialPeriodo;
	}

	public void setDataInicialPeriodo(Date dataInicialPeriodo) {
		this.dataInicialPeriodo = dataInicialPeriodo;
	}

	public Date getDataFinalPeriodo() {
		return dataFinalPeriodo;
	}

	public void setDataFinalPeriodo(Date dataFinalPeriodo) {
		this.dataFinalPeriodo = dataFinalPeriodo;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}
	
	
	
	
	

}

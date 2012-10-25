package com.claro.cobillingweb.persistence.view;

import java.io.Serializable;
import java.util.Date;

public class BatimentoWruppPrePagoView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date data;
	private String cdEOTLD;
	private String cdEOTClaro;
	private Long qtdChamadasWrupp;
	private Double vlrBrutoWrupp;
	private Long qtdChamadasScc;
	private Double vlrBrutoScc;
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getCdEOTLD() {
		return cdEOTLD;
	}
	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}
	public String getCdEOTClaro() {
		return cdEOTClaro;
	}
	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
	}
	public Long getQtdChamadasWrupp() {
		return qtdChamadasWrupp;
	}
	public void setQtdChamadasWrupp(Long qtdChamadasWrupp) {
		this.qtdChamadasWrupp = qtdChamadasWrupp;
	}
	public Double getVlrBrutoWrupp() {
		return vlrBrutoWrupp;
	}
	public void setVlrBrutoWrupp(Double vlrBrutoWrupp) {
		this.vlrBrutoWrupp = vlrBrutoWrupp;
	}
	public Long getQtdChamadasScc() {
		return qtdChamadasScc;
	}
	public void setQtdChamadasScc(Long qtdChamadasScc) {
		this.qtdChamadasScc = qtdChamadasScc;
	}
	public Double getVlrBrutoScc() {
		return vlrBrutoScc;
	}
	public void setVlrBrutoScc(Double vlrBrutoScc) {
		this.vlrBrutoScc = vlrBrutoScc;
	}
	
	
}

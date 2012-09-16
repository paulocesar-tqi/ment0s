package com.claro.sccweb.service.to;

import java.util.Date;

import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccPeriodicidadeRepasse;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;


/**
 * Transfer Object que serve como argumento para pesquisas de repasses.
 * Os itens de repasse são agrupados.
 */
public class ConsultaRepassePosTO {

	
	
	/**
	 * EOT da operadora Claro segundo {@link SccOperadora}
	 */
	private String cdEOTClaro; 
	
	/**
	 * EOT da operadora Externa (LD) segundo {@link SccOperadora}
	 */
	private String cdEOTLD;
	
	/**
	 * Produto de cobilling de acordo com {@link SccProdutoCobilling}
	 */
	private Long cdProdutoCobilling;
	
	/**
	 * Data inicial do repasse.
	 */
	private Date dtInicialRepasse;
	
	/**
	 * Periodicidade do repasse segundo {@link SccPeriodicidadeRepasse}
	 */
	private Long cdPeriodicidade;
	
	/**
	 * C = Confirmado.
	 * N = Cancelado.
	 */
	private String cdStatusRepasse;
	
	/**
	 * Data final do período de repasse.
	 */
	/*Esse campo será removido na revisão de código. Ele não tem utilidade para a pesquisa de repasse.*/
	private Date dtFinalRepasse;
	
	/**
	 * Em caso de TRUE irá totalizar o repasse usando todoas as operadoras da holding.
	 */
	private boolean holding;
	
	private String mesAno;
	
	
	
	
	public String getCdEOTClaro() {
		return cdEOTClaro;
	}
	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
	}
	public String getCdEOTLD() {
		return cdEOTLD;
	}
	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}
	public Long getCdProdutoCobilling() {
		return cdProdutoCobilling;
	}
	public void setCdProdutoCobilling(Long cdProdutoCobilling) {
		this.cdProdutoCobilling = cdProdutoCobilling;
	}
	public Date getDtInicialRepasse() {
		return dtInicialRepasse;
	}
	public void setDtInicialRepasse(Date dtInicialRepasse) {
		this.dtInicialRepasse = dtInicialRepasse;
	}
	public Long getCdPeriodicidade() {
		return cdPeriodicidade;
	}
	public void setCdPeriodicidade(Long cdPeriodicidade) {
		this.cdPeriodicidade = cdPeriodicidade;
	}
	public String getCdStatusRepasse() {
		return cdStatusRepasse;
	}
	public void setCdStatusRepasse(String cdStatusRepasse) {
		this.cdStatusRepasse = cdStatusRepasse;
	}
	public Date getDtFinalRepasse() {
		return dtFinalRepasse;
	}
	public void setDtFinalRepasse(Date dtFinalRepasse) {
		this.dtFinalRepasse = dtFinalRepasse;
	}
	public boolean isHolding() {
		return holding;
	}
	public void setHolding(boolean holding) {
		this.holding = holding;
	}
	public String getMesAno() {
		return mesAno;
	}
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	
	
	
}

/**
 * 
 */
package com.claro.cobillingweb.persistence.view;

import java.util.Date;


/**
 * @author 92038883
 *
 */
public class SccPosRelatorioDisputaDetalheView {

	private Long sqDisputa;
	private String cdEotLD;
	private String cdStatusDisputa;
	private String inRiscoDisputa;
	private Date dtCriacao;
	private Date dtAtualizacao;
	private String cdUsuarioManut;
	
	
	
	public Long getSqDisputa() {
		return sqDisputa;
	}
	public void setSqDisputa(Long sqDisputa) {
		this.sqDisputa = sqDisputa;
	}
	public String getCdEotLD() {
		return cdEotLD;
	}
	public void setCdEotLD(String cdEotLD) {
		this.cdEotLD = cdEotLD;
	}
	public String getCdStatusDisputa() {
		return cdStatusDisputa;
	}
	public void setCdStatusDisputa(String cdStatusDisputa) {
		this.cdStatusDisputa = cdStatusDisputa;
	}
	public Date getDtCriacao() {
		return dtCriacao;
	}
	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}
	public Date getDtAtualizacao() {
		return dtAtualizacao;
	}
	public void setDtAtualizacao(Date dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}
	public String getCdUsuarioManut() {
		return cdUsuarioManut;
	}
	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
	}
	public String getInRiscoDisputa() {
		return inRiscoDisputa;
	}
	public void setInRiscoDisputa(String inRiscoDisputa) {
		this.inRiscoDisputa = inRiscoDisputa;
	}

}

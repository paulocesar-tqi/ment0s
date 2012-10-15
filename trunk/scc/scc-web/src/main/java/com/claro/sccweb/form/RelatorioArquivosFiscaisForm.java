/**
 * 
 */
package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.view.SccArquivosFiscaisView;

/**
 * @author 92031709
 *
 */
public class RelatorioArquivosFiscaisForm extends BaseForm {
	
	private SccArquivosFiscaisView entity;
	
	private String sgUF;
	
	private String cdStatusArquivo;
	
	private String cdCSP;
	
	private Integer cdCiclo;
	
	private Integer mesCiclo;
	
	private Integer anoCiclo;

	public SccArquivosFiscaisView getEntity() {
		return entity;
	}

	public void setEntity(SccArquivosFiscaisView entity) {
		this.entity = entity;
	}

	public String getSgUF() {
		return sgUF;
	}

	public void setSgUF(String sgUF) {
		this.sgUF = sgUF;
	}

	public String getCdStatusArquivo() {
		return cdStatusArquivo;
	}

	public void setCdStatusArquivo(String cdStatusArquivo) {
		this.cdStatusArquivo = cdStatusArquivo;
	}

	public String getCdCSP() {
		return cdCSP;
	}

	public void setCdCSP(String cdCSP) {
		this.cdCSP = cdCSP;
	}

	public Integer getCdCiclo() {
		return cdCiclo;
	}

	public void setCdCiclo(Integer cdCiclo) {
		this.cdCiclo = cdCiclo;
	}

	public Integer getMesCiclo() {
		return mesCiclo;
	}

	public void setMesCiclo(Integer mesCiclo) {
		this.mesCiclo = mesCiclo;
	}

	public Integer getAnoCiclo() {
		return anoCiclo;
	}

	public void setAnoCiclo(Integer anoCiclo) {
		this.anoCiclo = anoCiclo;
	}
	
}

/**
 * 
 */
package com.claro.sccweb.form;

import java.util.Date;

import com.claro.cobillingweb.persistence.view.SccBatimentoArquivosView;

/**
 * @author 93046251
 *
 */
public class RelatorioBatimentoArquivosForm extends BaseForm {
	
	private SccBatimentoArquivosView entity;
	
	private String cdEOTLD;
	
	private String cdEOTClaro;
	
	private String tpArquivo;
	
	private Date dtInicioBatimento;
	
	private Date dtFimBatimento;
	
	
	
	
	public SccBatimentoArquivosView getEntity() {
		return entity;
	}

	public void setEntity(SccBatimentoArquivosView entity) {
		this.entity = entity;
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

	public String getTpArquivo() {
		return tpArquivo;
	}

	public void setTpArquivo(String tpArquivo) {
		this.tpArquivo = tpArquivo;
	}

	public Date getDtInicioBatimento() {
		return dtInicioBatimento;
	}

	public void setDtInicioBatimento(Date dtInicioBatimento) {
		this.dtInicioBatimento = dtInicioBatimento;
	}

	public Date getDtFimBatimento() {
		return dtFimBatimento;
	}

	public void setDtFimBatimento(Date dtFimBatimento) {
		this.dtFimBatimento = dtFimBatimento;
	}

	
	
	
	

}

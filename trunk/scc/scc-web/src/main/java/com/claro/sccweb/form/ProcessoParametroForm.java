/**
 * 
 */
package com.claro.sccweb.form;

import java.util.Date;

import com.claro.cobillingweb.persistence.entity.SccParamProcesso;
import com.claro.sccweb.dto.SccParamProcessoDto;

/**
 * @author 93046251
 *
 */
public class ProcessoParametroForm extends BaseForm {
	
	private SccParamProcessoDto entity;
	
	private Date dataInicial;
	
	private Date dataFinal;
	
	private String tipoProcesso;
	

	public SccParamProcessoDto getEntity() {
		return entity;
	}

	public void setEntity(SccParamProcessoDto entity) {
		this.entity = entity;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getTipoProcesso() {
		return tipoProcesso;
	}

	public void setTipoProcesso(String tipoProcesso) {
		this.tipoProcesso = tipoProcesso;
	}

	
	
	

}

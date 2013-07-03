/**
 * 
 */
package com.claro.sccweb.form;

import java.util.List;

import com.claro.cobillingweb.persistence.entity.SccDadosBancario;


/**
 * @author vagner.souza
 *
 */
public class CadastroDadosBancariosForm extends BaseForm {
	
	private SccDadosBancario entidade;
	
	private List<SccDadosBancario> lstBancario;

	public SccDadosBancario getEntidade() {
		return entidade;
	}

	public void setEntidade(SccDadosBancario entidade) {
		this.entidade = entidade;
	}

	public List<SccDadosBancario> getLstBancario() {
		return lstBancario;
	}

	public void setLstBancario(List<SccDadosBancario> lstBancario) {
		this.lstBancario = lstBancario;
	}
	
	
}

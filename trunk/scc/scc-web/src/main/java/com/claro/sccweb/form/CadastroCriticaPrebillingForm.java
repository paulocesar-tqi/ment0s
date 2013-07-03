/**
 * 
 */
package com.claro.sccweb.form;

import java.util.List;

import com.claro.cobillingweb.persistence.entity.SccCriticaPrebilling;



/**
 * @author vagner.souza
 *
 */
public class CadastroCriticaPrebillingForm extends BaseForm {
	
	private SccCriticaPrebilling entidade;
	
	private List<SccCriticaPrebilling> lstCritica;

	public SccCriticaPrebilling getEntidade() {
		return entidade;
	}

	public void setEntidade(SccCriticaPrebilling entidade) {
		this.entidade = entidade;
	}

	public List<SccCriticaPrebilling> getLstCritica() {
		return lstCritica;
	}

	public void setLstCritica(List<SccCriticaPrebilling> lstCritica) {
		this.lstCritica = lstCritica;
	}
	
	
	
	
	

}

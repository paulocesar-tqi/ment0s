package com.claro.sccweb.form;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.sccweb.service.to.ConsultaRepassePreTO;

public class ConsultaRepassePreForm extends BaseForm {

	private ConsultaRepassePreTO to;
	
	public ConsultaRepassePreForm() {
		to = new ConsultaRepassePreTO();
		to.setCdEOTClaro(BasicDAO.GET_ALL_STRING);
	}
	
	private String efetivarLigado = "N";
	
	public String getEfetivarLigado() {
		return efetivarLigado;
	}
	
	public void setEfetivarLigado(String efetivarLigado) {
		this.efetivarLigado = efetivarLigado;
	}
	
	public ConsultaRepassePreTO getTo() {
		return to;
	}
	
	public void setTo(ConsultaRepassePreTO to) {
		this.to = to;
	}
	
}

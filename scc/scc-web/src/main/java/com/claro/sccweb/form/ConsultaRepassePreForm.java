package com.claro.sccweb.form;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.sccweb.decorator.ConsultaRepassePrePagoDecorator;
import com.claro.sccweb.service.to.ConsultaRepassePreTO;

public class ConsultaRepassePreForm extends BaseForm {

	private ConsultaRepassePreTO to;
	
	private List<ConsultaRepassePrePagoDecorator> resultados;
	
	public ConsultaRepassePreForm() {
		to = new ConsultaRepassePreTO();
		to.setCdEOTClaro(BasicDAO.GET_ALL_STRING);
	}
	
	private String efetivarLigado = "N";
	
	private List<ConsultaRepassePrePagoDecorator> listaConsultaRepassePre;
	
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
	public List<ConsultaRepassePrePagoDecorator> getListaConsultaRepassePre() {
		return listaConsultaRepassePre;
	}

	public void setListaConsultaRepassePre(
			List<ConsultaRepassePrePagoDecorator> listaConsultaRepassePre) {
		this.listaConsultaRepassePre = listaConsultaRepassePre;
	}

	public List<ConsultaRepassePrePagoDecorator> getResultados() {
		return resultados;
	}

	public void setResultados(List<ConsultaRepassePrePagoDecorator> resultados) {
		this.resultados = resultados;
	}
	
	
	
}

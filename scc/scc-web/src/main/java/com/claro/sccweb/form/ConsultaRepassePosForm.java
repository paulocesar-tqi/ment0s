package com.claro.sccweb.form;

import java.util.List;

import com.claro.cobillingweb.persistence.entity.SccPeriodicidadeRepasse;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.sccweb.service.to.ConsultaRepassePosTO;

/**
 * Form object para a consulta de repasses pós-pago.
 * Como o objetivo da tela é a pesquisa (consulta) de repasses , o form é uma heranã de {@link ConsultaRepassePosTO}
 *
 */
public class ConsultaRepassePosForm extends ConsultaRepassePosTO {

	/**
	 * Campo hidden que identifica a operação que o usuário deseja executar.
	 */
	private String operacao;
	
	/**
	 * Mês do relatório.
	 */
	private Long mesRelatorio;
	
	/**
	 * Ano do relatório.
	 */
	private Long anoRelatorio;

	
	private List<SccProdutoCobilling> listProdutos;
	private List<SccPeriodicidadeRepasse> listPeriodos;

	

	public List<SccPeriodicidadeRepasse> getListPeriodos() {
		return listPeriodos;
	}

	public void setListPeriodos(List<SccPeriodicidadeRepasse> listPeriodos) {
		this.listPeriodos = listPeriodos;
	}

	public List<SccProdutoCobilling> getListProdutos() {
		return listProdutos;
	}

	public void setListProdutos(List<SccProdutoCobilling> listProdutos) {
		this.listProdutos = listProdutos;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public Long getMesRelatorio() {
		return mesRelatorio;
	}

	public void setMesRelatorio(Long mesRelatorio) {
		this.mesRelatorio = mesRelatorio;
	}

	public Long getAnoRelatorio() {
		return anoRelatorio;
	}

	public void setAnoRelatorio(Long anoRelatorio) {
		this.anoRelatorio = anoRelatorio;
	}
	
	
	
}

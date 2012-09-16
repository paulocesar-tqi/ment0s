package com.claro.sccweb.form;

import java.util.List;

import com.claro.sccweb.service.composite.SolicitacaoRepassePosComposite;
import com.claro.sccweb.service.to.SolicitacaoRepassePosPagoTO;

/**
 * Form back object para solicita��o de relat�rio de repasse para p�s-pago.
 * Como na mesma p�gina para listagem de relat�rios � poss�vel solicitar novo relat�rio , o form herda
 * de {@link SolicitacaoRepassePosPagoTO}.
 *
 */
public class SolicitacaoRepassePosPagoForm extends SolicitacaoRepassePosPagoTO {

	
	/**
	 * Indica se o usu�rio vai realizar uma pesquisa usando o form como filtro ou se ele deseja inserir uma nova requisi��o de relat�rio.
	 * Valores poss�veis : INCLUDE/DELETE .
	 */
	private String operacao;
	
	/**
	 * Quantidade de solicita��es de relat�rios criados.
	 */
	private Integer criados;
	
	/**
	 * Lista com os �ltimos 100 relat�rios de repasse p�s pago j� processados.
	 */
	private List<SolicitacaoRepassePosComposite> relatoriosProcessados;
	
	

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public Integer getCriados() {
		return criados;
	}

	public void setCriados(Integer criados) {
		this.criados = criados;
	}

	public List<SolicitacaoRepassePosComposite> getRelatoriosProcessados() {
		return relatoriosProcessados;
	}

	public void setRelatoriosProcessados(
			List<SolicitacaoRepassePosComposite> relatoriosProcessados) {
		this.relatoriosProcessados = relatoriosProcessados;
	}	
	
	
	
}

package com.claro.sccweb.form;

import java.util.List;

import com.claro.sccweb.service.composite.SolicitacaoRepassePosComposite;
import com.claro.sccweb.service.to.SolicitacaoRepassePosPagoTO;

/**
 * Form back object para solicitação de relatório de repasse para pós-pago.
 * Como na mesma página para listagem de relatórios é possível solicitar novo relatório , o form herda
 * de {@link SolicitacaoRepassePosPagoTO}.
 *
 */
public class SolicitacaoRepassePosPagoForm extends SolicitacaoRepassePosPagoTO {

	
	/**
	 * Indica se o usuário vai realizar uma pesquisa usando o form como filtro ou se ele deseja inserir uma nova requisição de relatório.
	 * Valores possíveis : INCLUDE/DELETE .
	 */
	private String operacao;
	
	/**
	 * Quantidade de solicitações de relatórios criados.
	 */
	private Integer criados;
	
	/**
	 * Lista com os últimos 100 relatórios de repasse pós pago já processados.
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

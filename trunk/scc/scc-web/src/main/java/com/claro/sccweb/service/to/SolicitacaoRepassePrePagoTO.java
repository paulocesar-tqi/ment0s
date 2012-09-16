package com.claro.sccweb.service.to;

import java.util.ArrayList;
import java.util.List;

import com.claro.cobillingweb.persistence.entity.SccParamProcessoPK;

/**
 * Argumento de entrada para servi�o de solicita��o de relat�rio de
 * repasse para pr�-pago.
 *
 */
public class SolicitacaoRepassePrePagoTO {

	/**
	 * Se TRUE informar� ao servi�o que a informa��o das cahves prim�rias geradas � necess�ria e precisa ser retornada.
	 */
	private boolean returnPks = false;
	
	/**
	 * Em caso de nova solicita��o, essa propriedade receber� o valor da(s) nova(s) entidade(s) criada.
	 */
	private List<SccParamProcessoPK> pksSolicitacao = new ArrayList<SccParamProcessoPK>();
	
	/**
	 * Usu�rio que solicitou a modifica��o/cria��o da solicita��o.
	 */
	private String userName;
	
	/**
	 * M�s do relat�rio.
	 */
	private Long mesRelatorio;
	
	/**
	 * Ano do relat�rio.
	 */
	private Long anoRelatorio;
	
	/**
	 * EOT da operadora externa.
	 */
	private String cdEOTLD;
	
	/**
	 * EOT da operadora Claro.
	 */
	private String cdEODClaro;
	
	/**
	 * Produto pr�-pago selecionado.
	 */
	private String cdProdutoPrepago;
	
	/**
	 * Crit�rio de custo.
	 */
	private String cdCriterioCusto;

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

	public String getCdEOTLD() {
		return cdEOTLD;
	}

	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}

	public String getCdEODClaro() {
		return cdEODClaro;
	}

	public void setCdEODClaro(String cdEODClaro) {
		this.cdEODClaro = cdEODClaro;
	}

	public String getCdProdutoPrepago() {
		return cdProdutoPrepago;
	}

	public void setCdProdutoPrepago(String cdProdutoPrepago) {
		this.cdProdutoPrepago = cdProdutoPrepago;
	}

	public String getCdCriterioCusto() {
		return cdCriterioCusto;
	}

	public void setCdCriterioCusto(String cdCriterioCusto) {
		this.cdCriterioCusto = cdCriterioCusto;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<SccParamProcessoPK> getPksSolicitacao() {
		return pksSolicitacao;
	}

	public void setPksSolicitacao(List<SccParamProcessoPK> pksSolicitacao) {
		this.pksSolicitacao = pksSolicitacao;
	}

	public boolean isReturnPks() {
		return returnPks;
	}

	public void setReturnPks(boolean returnPks) {
		this.returnPks = returnPks;
	}

	
	
	
}

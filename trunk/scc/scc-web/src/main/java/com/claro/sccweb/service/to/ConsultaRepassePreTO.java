package com.claro.sccweb.service.to;

import com.claro.cobillingweb.persistence.entity.SccPreFechamento;

/**
 * Argumento do m�todo de servi�o que realiza pesquisa em repasses do pr�-pago.
 *
 */
public class ConsultaRepassePreTO {
	
	/**
	 * Caso seja TRUE indica que a pesqusia dever� contar todas as ooperadoras da holding cdEOTClaro.
	 * Importante : Isso n�o significa que ser� feito um somat�rio dos valores de {@link SccPreFechamento} !!!!
	 */
	private boolean holding;
	
	private String cdEOTLD;
	
	/**
	 * EOT da operadora Claro ou BasicDAO.GET_ALL_STRING para selecionar todas.
	 */
	private String cdEOTClaro;
	
	/**
	 * EOT da operadora externa.
	 */
	private String cdProdutoPrepago;
	
	/**
	 * Status do repasse:
	 * 'C' = Confirmado.
	 * 'N' = Cancelado.
	 * BasicDAO.GET_ALL_STRING para selecionar todos.
	 */
	private String cdStatusRepasse;
	
	/**
	 * M�s do per�odo.
	 */
	private Long mes;
	
	/**
	 * Ano do per�odo.
	 */
	private Long ano;

	public String getCdEOTLD() {
		return cdEOTLD;
	}

	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}

	public String getCdProdutoPrepago() {
		return cdProdutoPrepago;
	}

	public void setCdProdutoPrepago(String cdProdutoPrepago) {
		this.cdProdutoPrepago = cdProdutoPrepago;
	}

	public String getCdStatusRepasse() {
		return cdStatusRepasse;
	}

	public void setCdStatusRepasse(String cdStatusRepasse) {
		this.cdStatusRepasse = cdStatusRepasse;
	}

	public Long getMes() {
		return mes;
	}

	public void setMes(Long mes) {
		this.mes = mes;
	}

	public Long getAno() {
		return ano;
	}

	public void setAno(Long ano) {
		this.ano = ano;
	}

	public String getCdEOTClaro() {
		return cdEOTClaro;
	}

	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
	}

	public boolean isHolding() {
		return holding;
	}

	public void setHolding(boolean holding) {
		this.holding = holding;
	}

	
	
}

/**
 * 
 */
package com.claro.cobillingweb.persistence.view;

import java.util.Date;

/**
 * @author 92031709
 *
 */
public class SccArquivosNaoProcessadosView {

	private Date dtRelatorio;
	private String noArquivoRetorno;
	private Integer qtChamadas;
	private Integer qtMinutoTarifados;
	private Double vlLiquido;
	private Date dtRecebimento;
	
	public Date getDtRelatorio() {
		return dtRelatorio;
	}
	public void setDtRelatorio(Date dtRelatorio) {
		this.dtRelatorio = dtRelatorio;
	}
	public String getNoArquivoRetorno() {
		return noArquivoRetorno;
	}
	public void setNoArquivoRetorno(String noArquivoRetorno) {
		this.noArquivoRetorno = noArquivoRetorno;
	}
	public Integer getQtChamadas() {
		return qtChamadas;
	}
	public void setQtChamadas(Integer qtChamadas) {
		this.qtChamadas = qtChamadas;
	}
	public Integer getQtMinutoTarifados() {
		return qtMinutoTarifados;
	}
	public void setQtMinutoTarifados(Integer qtMinutoTarifados) {
		this.qtMinutoTarifados = qtMinutoTarifados;
	}
	public Double getVlLiquido() {
		return vlLiquido;
	}
	public void setVlLiquido(Double vlLiquido) {
		this.vlLiquido = vlLiquido;
	}
	public Date getDtRecebimento() {
		return dtRecebimento;
	}
	public void setDtRecebimento(Date dtRecebimento) {
		this.dtRecebimento = dtRecebimento;
	}
}
